package by.onyx.common.data.impl;

import by.onyx.common.pojo.Equipment;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.repositories.ReceivedRepairRepository;
import by.onyx.common.data.ReceivedRepairData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReceivedRepairDataImpl implements ReceivedRepairData {

    private static final Logger log = LoggerFactory.getLogger(ReceivedRepairData.class);

    @Autowired
    private ReceivedRepairRepository receivedRepairRepository;

    @Transactional(readOnly = true)
    public List<ReceivedRepair> getAll() {
        List<ReceivedRepair> result = receivedRepairRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public ReceivedRepair getById(int id) {
        ReceivedRepair result = null;
        try{
            result = receivedRepairRepository.findOne(id);
        }catch (Exception e){
            log.error("not found by id " + e);
        }
        return result;
    }

    @Transactional
    public List<ReceivedRepair> getByEquipmentTypeAndState(Equipment.EquipmentType type, boolean state) {
        List<ReceivedRepair> result = new ArrayList<ReceivedRepair>();
        List<ReceivedRepair> all = receivedRepairRepository.findAll();
        if(all != null){
            for (ReceivedRepair rr : all) {
                if(rr.getEquipment().getType().equals(type) && rr.isState() == state){
                    result.add(rr);
                }
            }
        }
        return result;
    }
    @Transactional
    public List<ReceivedRepair> getByState(boolean status) {
        List<ReceivedRepair> result = receivedRepairRepository.findByState(status);
        return result;
    }

    @Transactional
    public ReceivedRepair save(ReceivedRepair receivedRepair) {
        ReceivedRepair received = null;
        if(receivedRepair != null){
            try{
                received = receivedRepairRepository.saveAndFlush(receivedRepair);
                log.debug("Received Repair saved " + receivedRepair);
            }catch (Exception e){
                log.error("Received Repair can't save " + e);
            }
        }
        return received;
    }

    public void deleteByNumber(String number) {

    }
}
