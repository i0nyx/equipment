package by.onyx.common.data.impl;

import by.onyx.common.data.ComputerRepairData;
import by.onyx.common.pojo.ComputerRepair;
import by.onyx.common.repositories.ComputerRepairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComputerRepairDataImpl implements ComputerRepairData {

    private static final Logger log = LoggerFactory.getLogger(ComputerRepairData.class);

    @Autowired
    private ComputerRepairRepository computerRepository;

    public ComputerRepair save(ComputerRepair data) {
        ComputerRepair result = null;
        if(data != null){
            try {
                result = computerRepository.saveAndFlush(data);
                log.info("saved computer repair is true");
            }catch (Exception e){
                log.error("can't save computer repair" + e);
            }
        }else{
            log.info("object computer repair is null");
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<ComputerRepair> getAll() {
        return computerRepository.findAll();
    }
}
