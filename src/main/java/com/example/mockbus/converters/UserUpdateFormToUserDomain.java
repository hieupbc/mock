package com.example.mockbus.converters;

import com.example.mockbus.DTO.UserUpdateForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.RoleService;
import com.example.mockbus.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateFormToUserDomain implements
    Converter<UserUpdateForm, UserDomain> {

  private RoleService roleService;
  private UserService userService;


  public UserUpdateFormToUserDomain(RoleService roleService,
      UserService userService) {
    super();
    this.roleService = roleService;
    this.userService = userService;
  }


  @Override
  public UserDomain convert(UserUpdateForm source) {
    UserDomain userDomain = userService.find(source.getId()).get();
    userDomain.setName(source.getName());
    userDomain.setEmail(source.getEmail());
    userDomain.setEnabled(source.getEnabled());
    List<String> roles = source.getRoles();
    Set<RoleDomain> updateRoles = new HashSet<>();
    List<RoleDomain> all = roleService.findAll();
    for (RoleDomain roleDomain : all) {
      if (roles.contains(roleDomain.getName())) {
        updateRoles.add(roleDomain);
      }
    }
    if (updateRoles.contains(roleService.findByName("ROLE_ADMIN").get())) {
      updateRoles.add(roleService.findByName("ROLE_EMPLOYEE").get());
    }
    userDomain.setRoles(updateRoles);
    userDomain.setAccountBalance(source.getAccountBalance());
    return userDomain;
  }
}
