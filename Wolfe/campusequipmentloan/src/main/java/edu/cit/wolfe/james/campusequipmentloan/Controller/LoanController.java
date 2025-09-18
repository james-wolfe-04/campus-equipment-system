package edu.cit.wolfe.james.campusequipmentloan.Controller;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Equipment;
import edu.cit.wolfe.james.campusequipmentloan.Entities.Loan;
import edu.cit.wolfe.james.campusequipmentloan.Repositories.EquipmentRepository;
import edu.cit.wolfe.james.campusequipmentloan.Service.LoanService;
import edu.cit.wolfe.james.campusequipmentloan.DTO.LoanResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {
    private final LoanService loanService;
    private final EquipmentRepository equipmentRepo;

    public LoanController(LoanService loanService, EquipmentRepository equipmentRepo) {
        this.loanService = loanService;
        this.equipmentRepo = equipmentRepo;
    }

    @PostMapping("/loans")
    public LoanResponseDTO createLoan(@RequestParam Long equipmentId, @RequestParam Long studentId) {
        Loan loan = loanService.createLoan(equipmentId, studentId);
        return toDto(loan);
    }

    @PostMapping("/loans/{id}/return")
    public LoanResponseDTO returnLoan(@PathVariable Long id) {
        Loan loan = loanService.returnLoan(id);
        return toDto(loan);
    }

    @GetMapping("/equipment/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepo.findByAvailableTrue();
    }

    // Mapper method
    private LoanResponseDTO toDto(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(),
                loan.getEquipment().getName(),
                loan.getStudent().getName(),
                loan.getStartDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStatus().name(),
                loan.getPenalty()
        );
    }
}
