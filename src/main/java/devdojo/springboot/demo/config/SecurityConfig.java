
package devdojo.springboot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import devdojo.springboot.demo.services.CustomUserDetailService;

/**
 * SecurityConfig
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomUserDetailService customUserDetailService;

 

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .anyRequest().authenticated().and()
    .httpBasic().and()
    .csrf().disable();

  } 

  /*@Override
  public void configure(final HttpSecurity http) throws Exception {
  
    http.cors().and().csrf().disable().authorizeRequests()
    .antMatchers(HttpMethod.GET, SecurityConstants.SING_UP_URL).permitAll()
    .antMatchers("courses").hasAnyRole("USER");
    .and()
    .addFilter(new JWTAutheticantionFilter(authenticationManager()))
    .addFilter(new JWTAutheticantionFilter(authenticationManager(), customUserDetailService));


  }*/

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
  }

  /*
    // para poder gerar os usuarios em memoria e podermos relizar testes 
   * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
   * throws Exception {
   * auth.inMemoryAuthentication().withUser("user").password("{noop}password").
   * roles("USER") .and()
   * .withUser("admin").password("{noop}password").roles("USER","ADMIN"); }
   */

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}