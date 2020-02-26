package devdojo.springboot.demo.repositories;


import java.util.List;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import devdojo.springboot.demo.models.Course;

/**
 * CourseRepository
 */
@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course,Long>{

  List<Course> findByNomeIgnoreCaseContaining(String nome);


 
}