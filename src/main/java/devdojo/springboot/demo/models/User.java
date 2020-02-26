package devdojo.springboot.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * User
 */

@Data
@Entity
public class User extends AbstractEntity {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @NotEmpty
  @Column(unique = true)
  private String username;

  @NotEmpty
  @JsonIgnore
  private String password;

  @NotEmpty
  private String name;

  @NotEmpty
  private boolean admin;

  public void User() {

  }

}