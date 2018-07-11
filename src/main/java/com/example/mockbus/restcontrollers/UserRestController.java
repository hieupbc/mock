package com.example.mockbus.restcontrollers;

import com.example.mockbus.DTO.UserCreateForm;
import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.DTO.UserUpdateForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.exceptions.ResourceNotFoundException;
import com.example.mockbus.exceptions.ResponseMsg;
import com.example.mockbus.exceptions.ValidationFailedException;
import com.example.mockbus.services.UserService;
import com.example.mockbus.utils.Utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UserRestController {

  private UserService userService;
  private Utils utils;
  private Converter<UserDomain, UserUpdateForm> userDomainToUserUpdateForm;
  private Converter<UserCreateForm, UserDomain> userCreateFormToUserDomain;
  private Converter<UserUpdateForm, UserDomain> userUpdateFormToUserDomain;
  private PasswordEncoder passwordEncoder;

  public UserRestController(UserService userService, Utils utils
      , Converter<UserDomain, UserUpdateForm> userDomainToUserUpdateForm,
      Converter<UserCreateForm, UserDomain> userCreateFormToUserDomain,
      Converter<UserUpdateForm, UserDomain> userUpdateFormToUserDomain,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.utils = utils;
    this.userDomainToUserUpdateForm = userDomainToUserUpdateForm;
    this.userCreateFormToUserDomain = userCreateFormToUserDomain;
    this.userUpdateFormToUserDomain = userUpdateFormToUserDomain;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Get user infos with choosen page index and page size
    * @param page :page index, default 0
   * @param size: page size, default 20
   * @return List of {@link UserCreateForm}
   */
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public ResponseEntity<List<UserUpdateForm>> getAllUserPaging(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    if (page == null) {
      page = 0;
    }
    if (size == null) {
      size = 20;
    }
    Page<UserDomain> listUser = userService.findAll(page, size);
    List<UserUpdateForm> userCreateForms = new ArrayList<>();
    listUser.getContent().forEach(
        e -> userCreateForms.add(userDomainToUserUpdateForm.convert(e)));
    return ResponseEntity.ok(userCreateForms);
  }

  /**
   * get user data with selected id
   *
   * @param id selected user id
   * @return 1 {@link UserUpdateForm} object if found, throw {@link
   * ResourceNotFoundException} if not found
   */
  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Object> getUserById(@PathVariable long id) {
    Optional<UserDomain> user = userService.find(id);
    if (!user.isPresent()) {
      throw new ResourceNotFoundException("Accound ID: " + id);
    }
    return ResponseEntity.ok(userDomainToUserUpdateForm.convert(user.get()));
  }

  /**
   * create user fuction for admin
   *
   * @param user {@link UserCreateForm}
   * @param bindingResult for validation data of user
   * @return list of {@link ResponseMsg} if validation failed, success message
   * if success
   */
  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Object> createUser(
      @Valid @RequestBody UserCreateForm user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }
    UserDomain userDomain = userCreateFormToUserDomain.convert(user);
    userService.create(userDomain);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseMsg("Status", "Success!"));
  }

  /* ---------------- DELETE USER ------------------------ */
  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Object> deleteUserById(@PathVariable long id) {
    Optional<UserDomain> user = userService.find(id);
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (!user.isPresent()) {
      throw new ResourceNotFoundException("Accound ID " + id);
    }
    if (authentication.getName().equals(user.get().getEmail())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(new ResponseMsg("Error", "You cant delete your own account"));
    }
    userService.delete(user.get());
    return ResponseEntity.ok().body(new ResponseMsg("Status", "Deleted!"));
  }

  /* ---------------- UPDATE USER ------------------------ */
  @RequestMapping(value = "/user", method = RequestMethod.PUT)
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public ResponseEntity<Object> updateUser(
      @Valid @RequestBody UserUpdateForm user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }
    boolean hasPermission = utils.hasPermission(user.getId(), "ROLE_ADMIN");
    Optional<UserDomain> oldUser = userService.findByEmail(user.getEmail());
    if (!oldUser.isPresent()) {
      throw new ResourceNotFoundException("Accound ID: " + user.getId());
    }
    if (!hasPermission) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
          .body(new ResponseMsg("ERROR", "DO NOT HAVE PERMISSION"));
    }
    // replace old user by new user.
    userService.update(userUpdateFormToUserDomain.convert(user));
    return ResponseEntity.ok(new ResponseMsg("Status", "Updated!"));
  }

  @RequestMapping(value = "/user/search/{name}", method = RequestMethod.GET)
  public ResponseEntity<List<UserUpdateForm>> getUserByEmail(
      @PathVariable String name,
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    if (page == null) {
      page = 0;
    }
    if (size == null) {
      size = 10;
    }
    Page<UserDomain> listUser = userService.findByNameIsLike(name, page, size);
    List<UserUpdateForm> userUpdateForms = new ArrayList<>();
    listUser.getContent().forEach(
        e -> userUpdateForms.add(userDomainToUserUpdateForm.convert(e)));
    return ResponseEntity.ok(userUpdateForms);
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> signUp(
      @Valid @ModelAttribute UserRegisterForm userRegisterForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      throw new ValidationFailedException(bindingResult);
    }
    UserDomain userDomain = userService.registerNewUser(userRegisterForm);
    Set<RoleDomain> roles = userDomain.getRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();
    roles.forEach(e -> authorities
        .add(new SimpleGrantedAuthority(e.getName())));

    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(userDomain.getEmail(),
            userDomain.getPassword(), authorities));

    return ResponseEntity.ok().body(new ResponseMsg("Status", "Success"));
  }

}