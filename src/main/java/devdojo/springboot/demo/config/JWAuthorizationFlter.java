package devdojo.springboot.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import devdojo.springboot.demo.services.CustomUserDetailService;
import io.jsonwebtoken.Jwts;


/**
 * JWAuthorizationFlter
 */
public class JWAuthorizationFlter extends BasicAuthenticationFilter {

  @Autowired
  private CustomUserDetailService customUserDetailService;

  public JWAuthorizationFlter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  protected void doFilterIntenal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String header = request.getHeader(SecurityConstants.HEADER_STRING);
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {

    String token = request.getHeader(SecurityConstants.HEADER_STRING);
    if(token == null ) return null;

    String username = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws((token.replace(SecurityConstants.TOKEN_PREFIX, ""))).getBody().getSubject();

    UserDetails userDetals = customUserDetailService.loadUserByUsername(username);

    return username != null ? new UsernamePasswordAuthenticationToken(username, null, userDetals.getAuthorities()): null;
  }
}