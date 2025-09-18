package edu.cit.wolfe.james.campusequipmentloan.DTO;

import java.time.LocalDate;

public class LoanResponseDTO {
    private Long id;
    private String equipmentName;
    private String studentName;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double penalty;

    // constructor
    public LoanResponseDTO(Long id, String equipmentName, String studentName,
                           LocalDate startDate, LocalDate dueDate, LocalDate returnDate,
                           String status, double penalty) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.studentName = studentName;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.penalty = penalty;
    }

    // getters only (immutable DTO)
    public Long getId() { return id; }
    public String getEquipmentName() { return equipmentName; }
    public String getStudentName() { return studentName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }
    public double getPenalty() { return penalty; }
}
