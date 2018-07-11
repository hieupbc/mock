package com.example.mockbus.JwtConfig;

import static com.example.mockbus.JwtConfig.AuthenticationFilter.HEADER_STRING;
import static com.example.mockbus.JwtConfig.AuthenticationFilter.SECRET;
import static com.example.mockbus.JwtConfig.AuthenticationFilter.TOKEN_PREFIX;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class AuthorizationFilter extends BasicAuthenticationFilter {

  private UserDetailsService userDetailsService;

  public AuthorizationFilter(AuthenticationManager authManager,
      UserDetailsService userDetailsService) {
    super(authManager);
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(req, res);
  }


  private UsernamePasswordAuthenticationToken getAuthentication(
      HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);

    if (token != null) {
      Claims claims = Jwts.parser()
          .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
          .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
      String user = claims.getSubject();
      if (user != null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user);
        return new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(), userDetails.getPassword(),
            userDetails.getAuthorities());
      }
      return null;
    }
    return null;
  }
}
