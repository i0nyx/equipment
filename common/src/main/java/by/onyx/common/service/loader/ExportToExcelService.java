package by.onyx.common.service.loader;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.service.loader.Equipment.ExportEquipmentToExcelTool;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static by.onyx.common.constants.ConstantsLoader.SHEET_EQUIPMENT_NAME;

public class ExportToExcelService {

    private EquipmentData equipmentData;

    public ExportToExcelService(EquipmentData equipmentData) {
        this.equipmentData = equipmentData;
    }

    public void processEquipmentSheer(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(SHEET_EQUIPMENT_NAME);
        ExportEquipmentToExcelTool exportEquipmentToExcelTool = new ExportEquipmentToExcelTool(equipmentData);
        exportEquipmentToExcelTool.fillEquipmentSheet(workbook, sheet);
    }
}
