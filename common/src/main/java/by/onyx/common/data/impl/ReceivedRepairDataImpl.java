package by.onyx.common.data.impl;

import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.pojo.EquipmentType;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.repositories.ReceivedRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ReceivedRepairDataImpl implements ReceivedRepairData {

    private ReceivedRepairRepository receivedRepairRepository;

    @Override
    public List<ReceivedRepair> getAll() {
        return receivedRepairRepository.findAll();
    }

    @Override
    public ReceivedRepair getById(int id) {
        ReceivedRepair result = null;
        try {
            result = receivedRepairRepository.findOne(id);
        } catch (Exception e) {
            log.error("not found by id " + e);
        }
        return result;
    }

    @Override
    public List<ReceivedRepair> getByEquipmentTypeAndState(EquipmentType type, boolean state) {
        List<ReceivedRepair> result = new ArrayList<>();
        List<ReceivedRepair> all = receivedRepairRepository.findAll();
        if (all != null) {
            for (ReceivedRepair rr : all) {
                if (rr.getEquipment().getType().equals(type) && rr.isState() == state) {
                    result.add(rr);
                }
            }
        }
        return result;
    }

    @Override
    public List<ReceivedRepair> getByState(boolean status) {
        return receivedRepairRepository.findByState(status);
    }

    @Override
    public ReceivedRepair save(ReceivedRepair receivedRepair) {
        ReceivedRepair received = null;
        if (receivedRepair != null) {
            try {
                received = receivedRepairRepository.saveAndFlush(receivedRepair);
                log.debug("Received Repair saved " + receivedRepair);
            } catch (Exception e) {
                log.error("Received Repair can't save " + e);
            }
        }
        return received;
    }

    @Override
    public void deleteByNumber(String number) {

    }
}
