package by.onyx.common.data;

import by.onyx.common.pojo.PrinterRepair;

import java.util.List;

public interface PrinterRepairData {
    List<PrinterRepair> getAll();
    PrinterRepair save(final PrinterRepair data);
}
