package xyz.jeevan.springwebfluxreactivemongo.controller;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import xyz.jeevan.springwebfluxreactivemongo.model.Student;
import xyz.jeevan.springwebfluxreactivemongo.model.StudentEvent;
import xyz.jeevan.springwebfluxreactivemongo.repository.StudentRepository;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

  @Autowired
  private StudentRepository studentRepository;

  @GetMapping("/{id}")
  public Mono<Student> getById(@PathVariable("id") final String id) {
    return studentRepository.findById(id);
  }

  @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<StudentEvent> getStudentEvents(@PathVariable("id") final String id) {
    return studentRepository.findById(id).flatMapMany(student -> {
      Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
      Flux<StudentEvent> studentEventFlux = Flux
          .fromStream(Stream.generate(() -> new StudentEvent("Exam", "Pune", new Date())));

      return Flux.zip(interval, studentEventFlux).map(Tuple2::getT2);
    });
  }
}
