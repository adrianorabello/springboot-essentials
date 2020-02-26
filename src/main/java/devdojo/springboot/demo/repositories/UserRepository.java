package devdojo.springboot.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import devdojo.springboot.demo.models.User;

/**
 * UserRepository
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long>{

  User findByUsername(String username);
}