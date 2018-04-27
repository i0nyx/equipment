package by.onyx.common.data.impl;

import by.onyx.common.data.PrinterRepairData;
import by.onyx.common.pojo.PrinterRepair;
import by.onyx.common.repositories.PrinterRepairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrinterRepairDataImpl implements PrinterRepairData {

    private static final Logger log = LoggerFactory.getLogger(PrinterRepairData.class);

    @Autowired
    private PrinterRepairRepository printerRepairRepository;

    @Transactional(readOnly = true)
    public List<PrinterRepair> getAll() {
        return printerRepairRepository.findAll();
    }

    @Transactional
    public PrinterRepair save(PrinterRepair data) {
        PrinterRepair result = null;
        if(data != null){
            try{
                result = printerRepairRepository.saveAndFlush(data);
                log.info("save printerRepair object");
            }catch (Exception e){
                log.error("can't save printerRepair object " + e);
            }
        }
        return result;
    }
}
