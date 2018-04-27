package by.onyx.common.data.impl;

import by.onyx.common.pojo.Equipment;
import by.onyx.common.repositories.EquipmentRepository;
import by.onyx.common.data.EquipmentData;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentDataImpl implements EquipmentData{

    private static final Logger log = LoggerFactory.getLogger(EquipmentData.class);

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Transactional
    public Equipment save(Equipment equipment) {
        Equipment result = null;
        if(equipment != null){
            try {
                result = equipmentRepository.saveAndFlush(equipment);
            }catch (Exception e){
                log.error("can't save equipment " + e);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Equipment> getAll() {
        List<Equipment> result = equipmentRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public Equipment getByCode(String code) {
        Equipment result = null;
        try{
           result = equipmentRepository.findByCode(code);
           Hibernate.initialize(result.getReceivedRepair());
        }catch (Exception e){
            log.error("no find by code " + e);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Equipment getById(int id) {
        Equipment result = null;
        try {
            result = equipmentRepository.findOne(id);
            Hibernate.initialize(result.getReceivedRepair());
        }catch (Exception e){
            log.info("no equipment by id - " + id + " " + e);
        }
        return result;
    }

    @Transactional
    public void deleteById(int id) {
        if(id != 0){
            try {
                equipmentRepository.delete(id);
                log.info("delete equipment by id " + id);
            }catch (Exception e){
                log.error("can't delete equipment by id " + id + " " + e);
            }
        }
    }
}
