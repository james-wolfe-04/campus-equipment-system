package edu.cit.wolfe.james.campusequipmentloan.Service;

import edu.cit.wolfe.james.campusequipmentloan.Entities.*;
import edu.cit.wolfe.james.campusequipmentloan.Repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class LoanService {
    private final LoanRepository loanRepo;
    private final EquipmentRepository equipmentRepo;
    private final StudentRepository studentRepo;

    private static final int MAX_ACTIVE = 2;
    private static final int LOAN_DAYS = 7;

    public LoanService(LoanRepository loanRepo, EquipmentRepository equipmentRepo, StudentRepository studentRepo) {
        this.loanRepo = loanRepo;
        this.equipmentRepo = equipmentRepo;
        this.studentRepo = studentRepo;
    }

    @Transactional
    public Loan createLoan(Long equipmentId, Long studentId) {
        Equipment equipment = equipmentRepo.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        if (!equipment.isAvailable()) {
            throw new RuntimeException("Equipment not available");
        }

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (loanRepo.countActiveLoansByStudentId(studentId) >= MAX_ACTIVE) {
            throw new RuntimeException("Student already has max active loans");
        }

        Loan loan = new Loan();
        loan.setEquipment(equipment);
        loan.setStudent(student);
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(LOAN_DAYS));
        loan.setStatus(LoanStatus.ACTIVE);

        equipment.setAvailable(false);
        equipmentRepo.save(equipment);

        return loanRepo.save(loan);
    }

    @Transactional
    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new RuntimeException("Already returned");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LocalDate.now().isAfter(loan.getDueDate()) ? LoanStatus.OVERDUE : LoanStatus.RETURNED);

        Equipment equipment = loan.getEquipment();
        equipment.setAvailable(true);
        equipmentRepo.save(equipment);

        return loanRepo.save(loan);
    }
}
