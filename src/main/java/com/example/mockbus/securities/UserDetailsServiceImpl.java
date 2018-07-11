package com.example.mockbus.securities;

import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.UserService;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String s) {
    Optional<UserDomain> userServiceByEmail = userService.findByEmail(s);
    if (!userServiceByEmail.isPresent()) {
      throw new UsernameNotFoundException(s);
    }
    UserDomain userDomain = userServiceByEmail.get();
//    return User.builder().username(userDomain.getName()).password(userDomain.getPassword()).authorities(userDomain.getRoles())
    return new User(s, userDomain.getPassword(),
        userDomain.isEnabled(), true, true, true,
        userDomain.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList()));
  }

}
