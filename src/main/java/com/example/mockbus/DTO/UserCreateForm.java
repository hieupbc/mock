package com.example.mockbus.DTO;

import com.example.mockbus.annotations.UniqueEmail;
import com.example.mockbus.annotations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateForm implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @NumberFormat
  private long id;

  @NotBlank
  @ValidEmail
  @UniqueEmail
  private String email;
  private boolean enabled;
  @NotBlank
  private String name;
  @NotBlank
  private String password;
  @NotNull
  private List<String> roles;

  private int accountBalance;

  public int getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(int accountBalance) {
    this.accountBalance = accountBalance;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public long getId() {
    return id;
  }

//    public String getCreated_at() {
//        return created_at;
//    }
//
//    public String getUpdated_at() {
//        return updated_at;
//    }

  public String getEmail() {
    return email;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  // Setter Methods

  public void setId(long id) {
    this.id = id;
  }

//    public void setCreated_at(String created_at) {
//        this.created_at = created_at;
//    }
//
//    public void setUpdated_at(String updated_at) {
//        this.updated_at = updated_at;
//    }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return this.name + "|" + this.email + this.id;
  }

}