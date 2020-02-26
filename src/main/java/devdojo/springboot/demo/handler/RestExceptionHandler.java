package devdojo.springboot.demo.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import devdojo.springboot.demo.error.ResourceNotFoundDetails;
import devdojo.springboot.demo.error.ResourceNotFoundException;
import devdojo.springboot.demo.error.ValidationErrorDetails;

/**
 * RestExceptionHandler
 */
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundExcpetion( ResourceNotFoundException rfnException ){

    ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder.newBuilder().
    timestamp(new Date().getTime())
    .status(HttpStatus.NOT_FOUND.value())
    .title("Resource not found ")
    .detail(rfnException.getMessage())
    .developerMessage(rfnException.getClass().getName())
    .build();


    return new ResponseEntity<>(rnfDetails,HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleResourceNotFoundExcpetion(MethodArgumentNotValidException manvException ){

     List<FieldError> fieldErros = manvException.getBindingResult().getFieldErrors();

     String fields = fieldErros.stream().map(FieldError::getField).collect(Collectors.joining(","));
     String fieldMesages = fieldErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

    System.out.println();
    ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder.newBuilder().
    timestamp(new Date().getTime())
    .status(HttpStatus.BAD_REQUEST.value())
    .title("Field validation error")
    .field(fields)
    .fieldMessage(fieldMesages)
    .detail(manvException.getMessage())
    .developerMessage(manvException.getClass().getName())
    .build();


    return new ResponseEntity<>(rnfDetails,HttpStatus.BAD_REQUEST);
  }
  
}