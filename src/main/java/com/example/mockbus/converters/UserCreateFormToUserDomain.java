package com.example.mockbus.converters;

import com.example.mockbus.DTO.UserCreateForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.RoleService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserCreateFormToUserDomain implements
    Converter<UserCreateForm, UserDomain> {

  @Autowired
  private RoleService roleService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDomain convert(UserCreateForm source) {
    UserDomain user = new UserDomain();
    user.setName(source.getName());
    user.setEmail(source.getEmail());
    user.setPassword(passwordEncoder.encode(source.getPassword()));
    List<String> roles = source.getRoles();
    Set<RoleDomain> updateRoles = new HashSet<>();
    List<RoleDomain> all = roleService.findAll();
    for (RoleDomain roleDomain : all) {
      if (roles.contains(roleDomain.getName())) {
        updateRoles.add(roleDomain);
      }
    }
    user.setAccountBalance(source.getAccountBalance());
    if (updateRoles.contains(roleService.findByName("ROLE_ADMIN").get())) {
      updateRoles.add(roleService.findByName("ROLE_EMPLOYEE").get());
    }
    user.setRoles(updateRoles);
    return user;
  }
}
