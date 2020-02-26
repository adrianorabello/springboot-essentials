package devdojo.springboot.demo.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import devdojo.springboot.demo.models.User;
import devdojo.springboot.demo.services.CustomUserDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTAutheticantonFilter
 */
public class JWTAutheticantionFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAutheticantionFilter(AuthenticationManager autthenticantiManager, CustomUserDetailService customUserDetailService) {
    this.authenticationManager = autthenticantiManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

    
    try {

      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
    } catch (IOException e) {
    
      throw new RuntimeException(e);
    }

    //return super.attemptAuthentication(request, response);

  }

  protected void successssssfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,Authentication authResult){

    String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
    String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
    .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
    .compact();

    response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
  }

 
  
}