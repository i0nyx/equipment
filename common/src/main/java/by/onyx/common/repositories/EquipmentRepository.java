package by.onyx.common.repositories;

import by.onyx.common.pojo.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findByCode(String code);
}
