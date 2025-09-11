package edu.cit.wolfe.james.campusequipmentloan.Repositories;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailableTrue();
}
