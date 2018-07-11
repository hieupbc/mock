package com.example.mockbus.DTO;

import com.example.mockbus.annotations.ValidEmail;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserUpdateForm {

  private long id;

  @Email
  @NotBlank
  @ValidEmail
  private String email;

  private boolean enabled;

  @NotBlank
  private String name;

  @NotNull
  private List<String> roles;

  @NotNull
  private int accountBalance;

  public int getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(int accountBalance) {
    this.accountBalance = accountBalance;
  }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public String getName() {
    return name;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setName(String name) {
    this.name = name;
  }


  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

}