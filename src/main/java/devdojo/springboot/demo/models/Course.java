package devdojo.springboot.demo.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Course
 */

@Getter
@Setter
@Entity

public class Course extends AbstractEntity {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @NotEmpty
  private String nome;
  @NotEmpty
  private String email;

  public Course() {

  }



  @Override
  public String toString() {
    return "{" +
      " nome='" + nome + "'" +
      ", email='" + email + "'" +
      "}";
  }
   

}