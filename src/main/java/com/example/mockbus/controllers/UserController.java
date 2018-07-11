package com.example.mockbus.controllers;

import com.example.mockbus.DTO.UserCreateForm;
import com.example.mockbus.DTO.UserRegisterForm;
import com.example.mockbus.DTO.UserUpdateForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.exceptions.ResourceNotFoundException;
import com.example.mockbus.services.RoleService;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class UserController {

  private Utils utils;
  private UserService userService;
  private RoleService roleService;
  private Converter<UserDomain, UserUpdateForm> userDomainToUserUpdateForm;
  private Converter<UserUpdateForm, UserDomain> userUpdateFormToUserDomain;

  public UserController(UserService userService, RoleService roleService,
      Converter<UserDomain, UserUpdateForm> userDomainToUserUpdateForm,
      Converter<UserUpdateForm, UserDomain> userUpdateFormToUserDomain,
      Utils utils) {
    this.userService = userService;
    this.roleService = roleService;
    this.userDomainToUserUpdateForm = userDomainToUserUpdateForm;
    this.userUpdateFormToUserDomain = userUpdateFormToUserDomain;
    this.utils = utils;
  }

  @PostMapping("/register")
  public String submitSignup(@Valid UserRegisterForm userRegisterForm,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "register";
    }
    UserDomain userDomain = userService.registerNewUser(userRegisterForm);
    Set<RoleDomain> roles = userDomain.getRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();
    roles.forEach(e -> authorities
        .add(new SimpleGrantedAuthority(e.getName().toString())));

    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(userDomain.getEmail(),
            userDomain.getPassword(), authorities));

    return "home";
  }

  /**
   * return user management site with server paging
   *
   * @param page page index
   * @param size page size
   */
  @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ModelAndView getPagedUserList(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {
    ModelAndView modelAndView = new ModelAndView("admin/usermanager");

    if (page == null || page == 0) {
      page = 0;
    } else {
      page -= 1;
    }
    if (size == null) {
      size = 20;
    }
    Page<UserDomain> listUser = userService.findAll(page, size);
    int totalPages = listUser.getTotalPages();
    List<UserUpdateForm> userUpdateForms = new ArrayList<>();
    List<RoleDomain> roleList = roleService.findAll();
    listUser.getContent().forEach(
        e -> userUpdateForms.add(userDomainToUserUpdateForm.convert(e)));
    UserCreateForm userCreateForm = new UserCreateForm();
    UserUpdateForm userUpdateForm = new UserUpdateForm();

    modelAndView.addObject("listUser", userUpdateForms);
    modelAndView.addObject("roleList", roleList);
    modelAndView.addObject("totalPages", totalPages);
    modelAndView.addObject("userCreateForm", userCreateForm);
    modelAndView.addObject("userUpdateForm", userUpdateForm);
    return modelAndView;
  }

  @PostMapping("/user/{id}")
  @Secured("ROLE_USER")
  public long editUserById(@PathVariable long id) {
    /*
     * Authentication authentication =
     * SecurityContextHolder.getContext().getAuthentication(); String email =
     * authentication.getName(); Optional<UserDomain> optional =
     * userService.findByEmail(email); boolean hasPermission =
     * utils.hasPermission(id, RoleName.ROLE_ADMIN.name()); if
     * (optional.get().getId() != id && !hasPermission) { throw new
     * AccessDeniedException("You are not allowed to access this url"); }
     * ModelAndView modelAndView = new ModelAndView("userinfo");
     * modelAndView.addObject("userInfo", optional.get());
     */
    System.out.println(id);
    return id;
  }

  @GetMapping("/user/{id}")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public ModelAndView getUserById(@PathVariable long id) {
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String email = authentication.getName();
    Optional<UserDomain> optional = userService.findByEmail(email);
    boolean hasPermission = utils.hasPermission(id, "ROLE_ADMIN");
    if (optional.get().getId() != id && !hasPermission) {
      throw new AccessDeniedException("You are not allowed to access this url");
    }
    ModelAndView modelAndView = new ModelAndView("userinfo");
    modelAndView.addObject("userInfo", optional.get());
    return modelAndView;
  }

  // update date user's information
  @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ModelAndView updateUser(
      @Valid @ModelAttribute UserUpdateForm userUpdateForm,
      BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView("updateinforesponse");
    if (bindingResult.hasErrors()) {
      modelAndView.addObject("responseMessage", "Invalid Input");
      return modelAndView;
    }
    Optional<UserDomain> oldUser = userService.find(userUpdateForm.getId());
    if (!oldUser.isPresent()) {
      throw new ResourceNotFoundException(
          "Account ID: " + userUpdateForm.getId());
    }
    boolean hasPermission = utils
        .hasPermission(userUpdateForm.getId(), "ROLE_ADMIN");

    if (!hasPermission && (oldUser.get().getEmail() != userUpdateForm.getEmail()
        || oldUser.get().getId() != userUpdateForm.getId())) {
      modelAndView.addObject("responseMessage", "DO NOT HAVE PERMISSION");
    }
    UserDomain convert = userUpdateFormToUserDomain.convert(userUpdateForm);
    userService.update(convert);
    modelAndView.addObject("responseMessage", "Updated");
    return modelAndView;
  }

}
