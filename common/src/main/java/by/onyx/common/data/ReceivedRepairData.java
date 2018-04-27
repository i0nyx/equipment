package by.onyx.common.data;

import by.onyx.common.pojo.Equipment;
import by.onyx.common.pojo.ReceivedRepair;

import java.util.List;

public interface ReceivedRepairData {

    List<ReceivedRepair> getAll();

    ReceivedRepair getById(int id);

    List<ReceivedRepair> getByEquipmentTypeAndState(Equipment.EquipmentType type, boolean status);

    List<ReceivedRepair> getByState(boolean status);

    ReceivedRepair save(ReceivedRepair receivedRepair);

    void deleteByNumber(String number);
}
