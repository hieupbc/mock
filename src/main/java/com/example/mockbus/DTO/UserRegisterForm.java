package com.example.mockbus.DTO;

import com.example.mockbus.annotations.PasswordMatches;
import com.example.mockbus.annotations.UniqueEmail;
import com.example.mockbus.annotations.ValidEmail;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserRegisterForm {

  @NotBlank
  @Size(max = 40)
  private String name;

  @NotBlank
  @ValidEmail
  @UniqueEmail
  private String email;

  @NotBlank
  @Size(max = 100, min = 8)
  private String password;
  @NotBlank
  @Size(max = 100, min = 8)
  private String retypedPassword;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRetypedPassword() {
    return retypedPassword;
  }

  public void setRetypedPassword(String retypedPassword) {
    this.retypedPassword = retypedPassword;
  }
}
