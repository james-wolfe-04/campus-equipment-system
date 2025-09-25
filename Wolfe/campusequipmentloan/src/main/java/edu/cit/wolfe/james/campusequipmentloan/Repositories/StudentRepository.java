package edu.cit.wolfe.james.campusequipmentloan.Repositories;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email); // login with email
}
