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

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class EquipmentDataImpl implements EquipmentData {

    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment save(Equipment equipment) {
        Equipment result = null;
        if (equipment != null) {
            try {
                result = equipmentRepository.saveAndFlush(equipment);
            } catch (Exception e) {
                log.error("can't save equipment " + e);
            }
        }
        return result;
    }

    @Override
    public List<Equipment> getAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment getByCode(String code) {
        Equipment result = null;
        try {
            result = equipmentRepository.findByCode(code);
            Hibernate.initialize(result.getReceivedRepair());
        } catch (Exception e) {
            log.error("no find by code " + e);
        }
        return result;
    }

    @Override
    public Equipment getById(int id) {
        Equipment result = null;
        try {
            result = equipmentRepository.findOne(id);
            Hibernate.initialize(result.getReceivedRepair());
        } catch (Exception e) {
            log.info("no equipment by id - " + id + " " + e);
        }
        return result;
    }

    @Override
    public void deleteById(int id) {
        if (id != 0) {
            try {
                equipmentRepository.delete(id);
                log.info("delete equipment by id " + id);
            } catch (Exception e) {
                log.error("can't delete equipment by id " + id + " " + e);
            }
        }
    }
}
