package xyz.jeevan.springwebfluxreactivemongo;

import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.jeevan.springwebfluxreactivemongo.model.Student;
import xyz.jeevan.springwebfluxreactivemongo.repository.StudentRepository;

@SpringBootApplication
public class SpringWebfluxReactiveMongoApplication {

  @Bean
  CommandLineRunner seedData(StudentRepository studentRepository) {
    return args -> {
      studentRepository.deleteAll().subscribe(null, null, () -> {
        Stream.of(new Student(UUID.randomUUID().toString(), "Sachin", 12),
            new Student(UUID.randomUUID().toString(), "Rahul", 13),
            new Student(UUID.randomUUID().toString(), "Michael", 11),
            new Student(UUID.randomUUID().toString(), "Brian", 15),
            new Student(UUID.randomUUID().toString(), "Rickey", 17)
        ).forEach(student -> {
          studentRepository.save(student).subscribe(System.out::println);
        });
      });
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringWebfluxReactiveMongoApplication.class, args);
  }
}
