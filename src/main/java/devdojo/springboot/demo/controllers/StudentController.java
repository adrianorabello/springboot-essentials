
package devdojo.springboot.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devdojo.springboot.demo.error.ResourceNotFoundException;
import devdojo.springboot.demo.models.Student;
import devdojo.springboot.demo.services.StudentServer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * StudentController
 */
@RestController
@RequestMapping(value = "students")
public class StudentController {

  @Autowired
  private StudentServer service;

  @GetMapping
  public ResponseEntity<?> findAll() {

    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);

  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody Student entity) {

    return new ResponseEntity<>(service.save(entity), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    verifyIfCurseExist(id);

    service.delete(id);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {

    verifyIfCurseExist(id);

    Student student = service.findById(id);
    if(student == null ) throw new ResourceNotFoundException("Student not found for id :  " + id );
    return new ResponseEntity<>(student, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student entity) {

    verifyIfCurseExist(id);

    return new ResponseEntity<>(service.save(entity), HttpStatus.OK);
  }

  @GetMapping(value="/username/{username}")
  public ResponseEntity<?> findByName(@PathVariable String username) {
    
   return new ResponseEntity<>(service.findByName(username),HttpStatus.OK);
  }

  private void verifyIfCurseExist(Long id) {
    Student response = service.findById(id);
    if (response == null) {
      throw new ResourceNotFoundException("Student  não encontrado para o id : " + id);
      // throw new ResourceNotFoundDetails();
    }
  }
  



}