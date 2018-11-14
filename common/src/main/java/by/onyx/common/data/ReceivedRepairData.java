package by.onyx.common.data;

import by.onyx.common.pojo.EquipmentType;
import by.onyx.common.pojo.ReceivedRepair;

import java.util.List;

public interface ReceivedRepairData {
    List<ReceivedRepair> getAll();
    ReceivedRepair getById(final int id);
    List<ReceivedRepair> getByEquipmentTypeAndState(final EquipmentType type, final boolean status);
    List<ReceivedRepair> getByState(final boolean status);
    ReceivedRepair save(final ReceivedRepair receivedRepair);
    void deleteByNumber(final String number);
}
