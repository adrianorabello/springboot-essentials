package devdojo.springboot.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import devdojo.springboot.demo.models.User;
import devdojo.springboot.demo.repositories.UserRepository;

/**
 * CustomUserDetailService
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

   
    // para lançar uma esseção se não encontrar o username 
    User user = Optional.ofNullable(repository.findByUsername(username)).orElseThrow(() -> 
                        new UsernameNotFoundException("username not found Exception"));


      //User user = repository.findByUsername(username);

      List<GrantedAuthority> authoryListAdmin  = AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
      List<GrantedAuthority> authoryListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
    

      return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                    user.getPassword(), 
                                                                    user.isAdmin() ? authoryListAdmin: authoryListUser );
  }


 

  
}