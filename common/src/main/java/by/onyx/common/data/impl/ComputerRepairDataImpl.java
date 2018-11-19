package by.onyx.common.data.impl;

import by.onyx.common.data.ComputerRepairData;
import by.onyx.common.pojo.ComputerRepair;
import by.onyx.common.repositories.ComputerRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ComputerRepairDataImpl implements ComputerRepairData {
    private final ComputerRepairRepository computerRepository;

    @Override
    public ComputerRepair save(final ComputerRepair data) {
        Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("Error: computerRepair should not be null!"));
        return computerRepository.saveAndFlush(data);
    }

    @Override
    public List<ComputerRepair> getAll() {
        return computerRepository.findAll();
    }
}
