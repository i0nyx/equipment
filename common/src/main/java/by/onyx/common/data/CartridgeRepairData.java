package by.onyx.common.data;

import by.onyx.common.pojo.CartridgeRepair;
import by.onyx.common.pojo.TypeWork;

import java.util.HashMap;
import java.util.List;

public interface CartridgeRepairData {

    List<CartridgeRepair> get();

    HashMap<TypeWork, String> getTypeWork();

    CartridgeRepair save(CartridgeRepair data);

}
