package by.onyx.common.service.loader.Equipment;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.pojo.Equipment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

import static by.onyx.common.util.ExcelUtil.getIntCellValue;
import static by.onyx.common.util.ExcelUtil.getStringCellValue;

public class ImportEquipmentToExcelTool {

    private static final Logger log = LoggerFactory.getLogger(ImportEquipmentToExcelTool.class);

    private EquipmentData equipmentData;

    public ImportEquipmentToExcelTool(EquipmentData equipmentData){
        this.equipmentData = equipmentData;
    }

    public void readEquipmentSheet(Sheet sheet){
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            Row currentRow = rowIterator.next();
            if(currentRow.getRowNum() > 0){
                importEquipmentFromExcel(currentRow);
            }
        }

    }

    private void importEquipmentFromExcel(Row row){
        Equipment result = new Equipment();

        Cell idCell = row.getCell(0);
        result.setId(getIntCellValue(idCell));

        Cell nameCell = row.getCell(1);
        result.setName(nameCell.getStringCellValue());

        Cell brandCell = row.getCell(2);
        result.setBrand(brandCell.getStringCellValue());

        Cell modelCell = row.getCell(3);
        result.setModel(getStringCellValue(modelCell));

        Cell typeCell = row.getCell(4);
        Equipment.EquipmentType eType = null;
        for(Equipment.EquipmentType type : Equipment.EquipmentType.values()){
            if(type.toString().equals(typeCell.getStringCellValue().toUpperCase())){
                eType = type;
            }
        }
        result.setType(eType);

        Cell codeCell = row.getCell(5);
        result.setCode(codeCell.getStringCellValue().toUpperCase());

        Cell cabinetCell = row.getCell(6);
        result.setCabinet(getStringCellValue(cabinetCell));

        if(checkResultEquipment(result)){
            equipmentData.save(result);
        }else{
            log.info("Equipment is empty " + result);
        }

    }

    private boolean checkResultEquipment(Equipment e){
        boolean valid = true;
        if(e.getName() == null || e.getName() == ""){
            valid = false;
        }
        if(e.getBrand() == null || e.getBrand() == ""){
            valid = false;
        }
        if(e.getType() == null || e.getType().equals("")){
            valid = false;
        }
        if(e.getCode() == null || e.getCode() == ""){
            valid = false;
        }
        return valid;
    }
}
