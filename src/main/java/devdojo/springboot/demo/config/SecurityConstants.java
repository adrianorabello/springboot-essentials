

package devdojo.springboot.demo.config;

import java.util.concurrent.TimeUnit;

/**
 * SecurityConstants
 */
public class SecurityConstants {

  static final String SECRET = "DevDojoFoda";
  static final String TOKEN_PREFIX = "Bearer ";
  static final String HEADER_STRING = "Authorization";
  static final String SING_UP_URL = "/users/sign-up";
   
  static final long EXPIRATION_TIME = 86400000L;
  
  public static void main(String[] args) {
    System.out.println(TimeUnit.MICROSECONDS.convert(1, TimeUnit.DAYS));
  }
}