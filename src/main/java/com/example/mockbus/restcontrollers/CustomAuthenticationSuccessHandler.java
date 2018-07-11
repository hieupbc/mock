//package com.example.mockbus.restcontrollers;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.io.IOException;
//import java.util.Date;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//public class CustomAuthenticationSuccessHandler implements
//    AuthenticationSuccessHandler {
//
//  private static final long EXPIRATION_TIME = 60 * 60 * 24 * 1000;
//  public static final String SECRET = "setret";
//  public static final String TOKEN_PREFIX = "Bearer ";
//  public static final String HEADER_STRING = "Authorization";
//
//  @Override
//  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//      HttpServletResponse res, Authentication auth)
//      throws IOException, ServletException {
//    String token = Jwts.builder()
//        .setSubject(((User) auth.getPrincipal()).getUsername())
//        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//        .signWith(SignatureAlgorithm.HS512, SECRET)
//        .compact();
//    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//  }
//}
