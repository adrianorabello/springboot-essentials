
package devdojo.springboot.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devdojo.springboot.demo.models.Student;
import devdojo.springboot.demo.repositories.StudentRepository;

/**
 * StudentServer
 */
@Service
public class StudentServer {

  @Autowired
  private StudentRepository repository;

  public Iterable<Student> findAll() {
    return this.repository.findAll();
  }

  public Student save(Student object) {
    return repository.save(object);
  }

  public Student findById(Long id) {

   Optional<Student> response = repository.findById(id);

    return response.orElse(null);
  }

  public void delete(Long id){
    repository.deleteById(id);;
  }

  public List<Student> findByName(String username){

    return repository.findByNameIgnoreCaseContaining(username);
  }

}