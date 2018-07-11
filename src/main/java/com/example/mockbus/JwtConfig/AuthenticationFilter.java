package com.example.mockbus.JwtConfig;

import com.example.mockbus.DTO.UserLoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final long EXPIRATION_TIME = 60 * 60 * 24 * 1000;
  public static final String SECRET = "setret";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";

  public AuthenticationFilter(AuthenticationManager authManager) {
    setAuthenticationManager(authManager);
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
      HttpServletResponse res) throws AuthenticationException {
    try {
      UserLoginForm user = new ObjectMapper()
          .readValue(req.getInputStream(), UserLoginForm.class);
      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(
              user.getUsername(), user.getPassword(),
              new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException, ServletException {
    String token = Jwts.builder()
        .setSubject(((User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    Map<String, String> tokenMap = new HashMap<String, String>();
    tokenMap.put("token", token);
    res.setStatus(HttpStatus.OK.value());
    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    (new ObjectMapper()).writeValue(res.getWriter(), tokenMap);
//    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
