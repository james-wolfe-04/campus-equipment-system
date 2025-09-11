package edu.cit.wolfe.james.campusequipmentloan.Repositories;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.student.id = :studentId AND l.status = 'ACTIVE'")
    long countActiveLoansByStudentId(Long studentId);
}
