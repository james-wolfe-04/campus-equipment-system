package edu.cit.wolfe.james.campusequipmentloan.Repositories;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
