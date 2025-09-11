package edu.cit.wolfe.james.campusequipmentloan.Controller;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Equipment;
import edu.cit.wolfe.james.campusequipmentloan.Entities.Loan;
import edu.cit.wolfe.james.campusequipmentloan.Repositories.EquipmentRepository;
import edu.cit.wolfe.james.campusequipmentloan.Service.LoanService;
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
    public Loan createLoan(@RequestParam Long equipmentId, @RequestParam Long studentId) {
        return loanService.createLoan(equipmentId, studentId);
    }

    @PostMapping("/loans/{id}/return")
    public Loan returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

    @GetMapping("/equipment/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepo.findByAvailableTrue();
    }
}
