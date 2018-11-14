package by.onyx.common.data;

import by.onyx.common.pojo.Equipment;

import java.util.List;

public interface EquipmentData {
    Equipment save(final Equipment equipment);
    List<Equipment> getAll();
    Equipment getByCode(final String code);
    Equipment getById(final int id);
    void deleteById(final int id);
}
