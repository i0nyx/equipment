package by.onyx.common.data;

import by.onyx.common.pojo.CartridgeRepair;

import java.util.List;

public interface CartridgeRepairData {
    List<CartridgeRepair> get();

    CartridgeRepair save(final CartridgeRepair data);

}
