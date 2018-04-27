package by.onyx.common.data.impl;

import by.onyx.common.pojo.CartridgeRepair;
import by.onyx.common.repositories.CartridgeRepairRepository;
import by.onyx.common.data.CartridgeRepairData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static by.onyx.common.pojo.CartridgeRepair.typeWorkHashMap;

@Service
public class CartridgeRepairDataImpl implements CartridgeRepairData {

    private static final Logger log = LoggerFactory.getLogger(CartridgeRepairData.class);

    @Autowired
    private CartridgeRepairRepository repairRepository;

    public List<CartridgeRepair> get() {
        return repairRepository.findAll();
    }

    public HashMap<CartridgeRepair.TypeWork, String> getTypeWork() {
        return typeWorkHashMap;
    }

    @Transactional
    public CartridgeRepair save(CartridgeRepair data) {
        CartridgeRepair saved = null;
        if(data != null) {
            try{
                saved = repairRepository.saveAndFlush(data);
                log.debug("save cartridgeRepair: " + data);
            }catch (Exception e){
                log.error("can't save cartridgeRepair: " +  e);
            }
        }else{
            log.error("save cartridgeRepair null");
        }
        return saved;
    }
}
