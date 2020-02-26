package devdojo.springboot.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import devdojo.springboot.demo.models.Course;
import devdojo.springboot.demo.repositories.CourseRepository;

/**
 * CourseService
 */

@Service
public class CourseService {

  @Autowired
  private CourseRepository repository;

  public Iterable<?> findAll(Pageable pageable) {

    return repository.findAll(pageable);
  }

  public Course save(Course entity) {

    Course response = repository.save(entity);

    return response;
  }

  public List<Course> findByName(String nome) {

    return repository.findByNomeIgnoreCaseContaining(nome);
  }

  public Course findById(Long id) {

    Optional<Course> response = repository.findById(id);
    return response.orElse(null);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

}