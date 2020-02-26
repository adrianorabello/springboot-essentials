
package devdojo.springboot.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devdojo.springboot.demo.models.Student;





/**
 * StudentRepository
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Long>{

  List<Student> findByNameIgnoreCaseContaining(String name);
}