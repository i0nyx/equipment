package by.onyx.common.service.loader;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.service.loader.Equipment.ImportEquipmentToExcelTool;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static by.onyx.common.constants.ConstantsLoader.SHEET_EQUIPMENT_NAME;

public class ImportToExcelService {

    private EquipmentData equipmentData;

    public ImportToExcelService(EquipmentData equipmentData){
        this.equipmentData = equipmentData;
    }

    public void processEquipmentSheet(Workbook workbook){
        Sheet sheet = workbook.getSheet(SHEET_EQUIPMENT_NAME);
        if(sheet != null){
            ImportEquipmentToExcelTool importEquipmentToExcelTool = new ImportEquipmentToExcelTool(equipmentData);
            importEquipmentToExcelTool.readEquipmentSheet(sheet);
        }

    }
}
