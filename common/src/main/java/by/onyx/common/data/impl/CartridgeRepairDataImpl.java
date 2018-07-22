package by.onyx.common.data.impl;

import by.onyx.common.data.CartridgeRepairData;
import by.onyx.common.pojo.CartridgeRepair;
import by.onyx.common.pojo.TypeWork;
import by.onyx.common.repositories.CartridgeRepairRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static by.onyx.common.pojo.CartridgeRepair.typeWorkHashMap;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CartridgeRepairDataImpl implements CartridgeRepairData {

    private CartridgeRepairRepository repairRepository;

    @Override
    public List<CartridgeRepair> get() {
        return repairRepository.findAll();
    }

    @Override
    public HashMap<TypeWork, String> getTypeWork() {
        return typeWorkHashMap;
    }

    @Override
    public CartridgeRepair save(CartridgeRepair data) {
        CartridgeRepair saved = null;
        if (data != null) {
            try {
                saved = repairRepository.saveAndFlush(data);
                log.debug("save cartridgeRepair: " + data);
            } catch (Exception e) {
                log.error("can't save cartridgeRepair: " + e);
            }
        } else {
            log.error("save cartridgeRepair null");
        }
        return saved;
    }
}
