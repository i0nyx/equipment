package by.onyx.common.data.impl;

import by.onyx.common.data.CartridgeRepairData;
import by.onyx.common.pojo.CartridgeRepair;
import by.onyx.common.repositories.CartridgeRepairRepository;
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
public class CartridgeRepairDataImpl implements CartridgeRepairData {
    private final CartridgeRepairRepository repairRepository;

    @Override
    public List<CartridgeRepair> get() {
        return repairRepository.findAll();
    }

    @Override
    public CartridgeRepair save(final CartridgeRepair data) {
        Optional.ofNullable(data).orElseThrow(NullPointerException::new);
        return repairRepository.saveAndFlush(data);
    }
}