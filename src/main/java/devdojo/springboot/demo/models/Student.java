

package devdojo.springboot.demo.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Student
 */

@Entity
@Data
public class Student extends AbstractEntity{

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @NotEmpty
  private String name;

  @Email
  private String email;

  public Student(){
    
  }
  
}