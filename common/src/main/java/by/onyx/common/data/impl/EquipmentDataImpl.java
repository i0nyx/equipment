package by.onyx.common.data.impl;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.pojo.Equipment;
import by.onyx.common.repositories.EquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class EquipmentDataImpl implements EquipmentData {
    private final EquipmentRepository equipmentRepository;

    @Override
    public Equipment save(final Equipment data) {
        Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("Error: equipment should not be null!"));
        return equipmentRepository.saveAndFlush(data);
    }

    @Override
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getByCode(final String code) {
        Equipment result = equipmentRepository.findByCode(code);
        Optional.ofNullable(result).ifPresent(equipment -> Hibernate.initialize(equipment.getReceivedRepair()));
        return result;
    }

    @Override
    public Equipment getById(final int id) {
        Equipment result = equipmentRepository.findOne(id);
        Optional.ofNullable(result).ifPresent(equipment -> Hibernate.initialize(equipment.getReceivedRepair()));
        return result;
    }

    @Override
    public void deleteById(final int id) {
        try {
            equipmentRepository.delete(id);
            log.info("delete equipment by id {}", id);
        } catch (Exception e) {
            log.error("can't delete equipment by id {}, exception: {}", id, e);
        }
    }
}