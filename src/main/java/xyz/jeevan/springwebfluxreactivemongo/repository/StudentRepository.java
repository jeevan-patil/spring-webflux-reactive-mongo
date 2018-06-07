package xyz.jeevan.springwebfluxreactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import xyz.jeevan.springwebfluxreactivemongo.model.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

}
