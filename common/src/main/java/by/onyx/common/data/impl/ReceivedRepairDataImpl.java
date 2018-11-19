package by.onyx.common.data.impl;

import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.pojo.EquipmentType;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.repositories.ReceivedRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ReceivedRepairDataImpl implements ReceivedRepairData {
    private final ReceivedRepairRepository receivedRepairRepository;

    @Override
    public List<ReceivedRepair> getAll() {
        return receivedRepairRepository.findAll();
    }

    @Override
    public ReceivedRepair getById(final int id) {
        return receivedRepairRepository.findOne(id);
    }

    @Override
    public List<ReceivedRepair> getByEquipmentTypeAndState(final EquipmentType type, final boolean state) {
        return receivedRepairRepository.findAll().stream()
                .filter(receivedRepair -> receivedRepair.getEquipment().getType().equals(type))
                .filter(receivedRepair -> receivedRepair.isState() == state)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceivedRepair> getByState(final boolean status) {
        Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("Error: status should not be null!"));
        return receivedRepairRepository.findByState(status);
    }

    @Override
    public ReceivedRepair save(final ReceivedRepair receivedRepair) {
        Optional.ofNullable(receivedRepair).orElseThrow(() -> new IllegalArgumentException("Error: receivedRepair should not be null!"));
        return receivedRepairRepository.saveAndFlush(receivedRepair);
    }

    @Override
    public void deleteByNumber(String number) {
        //TODO
    }
}
