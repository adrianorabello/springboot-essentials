package devdojo.springboot.demo.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * AbstractEntity
 */
@MappedSuperclass
@Data
public class AbstractEntity implements Serializable {
 
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long id;

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    AbstractEntity that = (AbstractEntity) o;

    return id != null ? id.equals(that.id) : that.id == null;
  }

  @Override
  public int hashCode() {

    return id != null ? id.hashCode() : 0;
  }

  public AbstractEntity() {

  }

}