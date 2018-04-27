package by.onyx.common.data;

import by.onyx.common.pojo.CartridgeRepair;

import java.util.HashMap;
import java.util.List;

public interface CartridgeRepairData {

    List<CartridgeRepair> get();

    HashMap<CartridgeRepair.TypeWork, String> getTypeWork();

    CartridgeRepair save(CartridgeRepair data);

}
