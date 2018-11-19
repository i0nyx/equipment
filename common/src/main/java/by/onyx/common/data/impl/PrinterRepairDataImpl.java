package by.onyx.common.data.impl;

import by.onyx.common.data.PrinterRepairData;
import by.onyx.common.pojo.PrinterRepair;
import by.onyx.common.repositories.PrinterRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PrinterRepairDataImpl implements PrinterRepairData {
    private PrinterRepairRepository printerRepairRepository;

    @Override
    public List<PrinterRepair> getAll() {
        return printerRepairRepository.findAll();
    }

    @Override
    public PrinterRepair save(final PrinterRepair data) {
        Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("Error: printerRepair should not be null!"));
        return printerRepairRepository.saveAndFlush(data);
    }
}
