package com.example.mockbus.converters;

import com.example.mockbus.DTO.UserUpdateForm;
import com.example.mockbus.entities.RoleDomain;
import com.example.mockbus.entities.UserDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDomainToUserUpdateForm implements
    Converter<UserDomain, UserUpdateForm> {

  /**
   * Convert the source object of type {@code S} to target type {@code T}.
   *
   * @param source the source object to convert, which must be an instance of
   * {@code S} (never {@code null})
   * @return the converted object, which must be an instance of {@code T}
   * (potentially {@code null})
   * @throws IllegalArgumentException if the source cannot be converted to the
   * desired target type
   */
  @Override
  public UserUpdateForm convert(UserDomain source) {
    UserUpdateForm userUpdateForm = new UserUpdateForm();
    userUpdateForm.setId(source.getId());
    userUpdateForm.setName(source.getName());
    userUpdateForm.setEmail(source.getEmail());
    userUpdateForm.setEnabled(source.isEnabled());
    Set<RoleDomain> roles = source.getRoles();
    List<String> roleNames = new ArrayList<>();
    roles.forEach(e -> roleNames.add(e.getName()));
    userUpdateForm.setRoles(roleNames);
    userUpdateForm.setAccountBalance(source.getAccountBalance());
    return userUpdateForm;
  }
}
