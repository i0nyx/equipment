package by.onyx.common.service.loader.Equipment;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.pojo.Equipment;
import by.onyx.common.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExportEquipmentToExcelTool {

    private EquipmentData equipmentData;

    public ExportEquipmentToExcelTool(EquipmentData equipmentData) {
        this.equipmentData = equipmentData;
    }

    public void fillEquipmentSheet(XSSFWorkbook workbook, XSSFSheet sheet) {
        int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        createTitleToExcelSheet(row);
        List<Equipment> allEquipment = equipmentData.getAll();
        for (Equipment equipment : allEquipment) {
            rowNum++;
            createRowToExcelSheet(sheet, rowNum, equipment);
        }

    }

    private void createRowToExcelSheet(XSSFSheet sheet, int rowNum, Equipment equipment) {
        Row row = sheet.createRow(rowNum);

        ExcelUtil.createStringCell(row, 0, String.valueOf(equipment.getId()));
        ExcelUtil.createStringCell(row, 1, equipment.getName());
        ExcelUtil.createStringCell(row, 2, equipment.getBrand());
        ExcelUtil.createStringCell(row, 3, equipment.getModel());
        ExcelUtil.createStringCell(row, 4, equipment.getType().toString());
        ExcelUtil.createStringCell(row, 5, equipment.getCode());
        ExcelUtil.createStringCell(row, 6, equipment.getCabinet());

    }

    private void createTitleToExcelSheet(Row row) {
        Cell cell;

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ID");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("NAME");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("BRAND");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("MODEL");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("TYPE");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("CODE");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("CABINET");

    }
}
