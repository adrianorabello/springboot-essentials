package devdojo.springboot.demo.error;

import lombok.Data;

/**
 * ErrorDetail
 */

@Data
public class ErrorDetail {

  private String title;
  private int status;
  private String detail;
  private long timestamp;
  private String developerMessage;

  public ErrorDetail(){

  }


  
}