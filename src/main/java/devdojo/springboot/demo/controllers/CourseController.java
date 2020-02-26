package devdojo.springboot.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devdojo.springboot.demo.error.ResourceNotFoundException;

import devdojo.springboot.demo.models.Course;

import javax.naming.NameNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import devdojo.springboot.demo.services.CourseService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * CourseController
 */
@RestController
@RequestMapping(value = "courses")
@CrossOrigin

public class CourseController {

  @Autowired
  private CourseService service;

 //@ApiOperation(value = "Lista de cusos", notes = "retorna uma lista de cusos ", response = Course[].class, responseContainer = "List")
  @GetMapping
  public ResponseEntity<?> findAll(Pageable pagiable) {

    Iterable list = service.findAll(pagiable);
    //if(list == null) throw new ResourceNotFoundException("NNot found");
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> findBydId(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {

    System.out.println(userDetails.toString());
    this.verifyIfCurseExist(id);
    return new ResponseEntity<Course>(service.findById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody Course entity) {
    Course response = service.save(entity);
    return new ResponseEntity<Course>(response, HttpStatus.OK);
  }

  @GetMapping(value = "findByNome/{nome}")
  public ResponseEntity<java.util.List<Course>> findByName(@PathVariable String nome) {
    return new ResponseEntity<>(service.findByName(nome), HttpStatus.OK);
  }

  @PatchMapping(value = "/{id}")
  public ResponseEntity<?> update(@RequestBody Course entity) {

    this.verifyIfCurseExist(entity.getId());
    return new ResponseEntity<Course>(service.save(entity), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  @PreAuthorize("hasRole('auth')")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  private void verifyIfCurseExist(Long id) {
    Course response = service.findById(id);
    if (response == null) {
      throw new ResourceNotFoundException("Curso  não encontrado para o id : " + id);
      // throw new ResourceNotFoundDetails();
    }
  }

}