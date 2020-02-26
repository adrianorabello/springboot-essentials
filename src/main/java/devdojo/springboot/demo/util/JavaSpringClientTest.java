
package devdojo.springboot.demo.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import devdojo.springboot.demo.models.Course;


/**
 * JavaSpringClientTest
 */
public class JavaSpringClientTest {

  public static void main(String[] args) {

    String url = "http://localhost:8012/courses";

    RestTemplate restTemplate = new RestTemplateBuilder().rootUri(url).basicAuthorization("user", "devdojo").build();

    ResponseEntity<Course> course = restTemplate.getForEntity("/{id}", Course.class, 6L);

    System.out.println(course);

  }

}

/**
 * InnerJavaSpringClientTest
 */
