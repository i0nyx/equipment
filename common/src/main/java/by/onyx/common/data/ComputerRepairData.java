package by.onyx.common.data;

import by.onyx.common.pojo.ComputerRepair;

import java.util.List;

public interface ComputerRepairData {

    ComputerRepair save(ComputerRepair data);

    List<ComputerRepair> getAll();
}
