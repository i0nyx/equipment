package by.onyx.common.data.impl;

import by.onyx.common.data.PrinterRepairData;
import by.onyx.common.pojo.PrinterRepair;
import by.onyx.common.repositories.PrinterRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public PrinterRepair save(PrinterRepair data) {
        PrinterRepair result = null;
        if (data != null) {
            try {
                result = printerRepairRepository.saveAndFlush(data);
                log.info("save printerRepair object");
            } catch (Exception e) {
                log.error("can't save printerRepair object " + e);
            }
        }
        return result;
    }
}
