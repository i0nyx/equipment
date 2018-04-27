package by.onyx.common.data;

import by.onyx.common.pojo.Equipment;

import java.util.List;

public interface EquipmentData {

    Equipment save(Equipment equipment);

    List<Equipment> getAll();

    Equipment getByCode(String code);

    Equipment getById(int id);

    void deleteById(int id);
}
