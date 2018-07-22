package by.onyx.common.data.impl;

import by.onyx.common.data.ComputerRepairData;
import by.onyx.common.pojo.ComputerRepair;
import by.onyx.common.repositories.ComputerRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ComputerRepairDataImpl implements ComputerRepairData {

    private ComputerRepairRepository computerRepository;

    @Override
    public ComputerRepair save(ComputerRepair data) {
        ComputerRepair result = null;
        if (data != null) {
            try {
                result = computerRepository.saveAndFlush(data);
                log.info("saved computer repair is true");
            } catch (Exception e) {
                log.error("can't save computer repair" + e);
            }
        } else {
            log.info("object computer repair is null");
        }
        return result;
    }

    @Override
    public List<ComputerRepair> getAll() {
        return computerRepository.findAll();
    }
}
