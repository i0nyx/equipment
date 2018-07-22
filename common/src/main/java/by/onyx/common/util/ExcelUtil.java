package by.onyx.common.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {

    public static Cell createStringCell(Row row, int column, String value) {
        Cell cell;
        cell = row.createCell(column, CellType.STRING);
        cell.setCellValue(value);
        return cell;
    }

    public static String getStringCellValue(Cell cell) {
        String result = null;
        if (cell != null) {
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                result = String.valueOf((int) cell.getNumericCellValue());
            } else {
                result = cell.getStringCellValue();
            }
        }
        return result;
    }

    public static int getIntCellValue(Cell cell) {
        int result = 0;
        if (cell != null) {
            if (cell.getCellTypeEnum() == CellType.STRING) {
                result = Integer.valueOf(cell.getStringCellValue());
            } else {
                result = (int) cell.getNumericCellValue();
            }
        }
        return result;
    }
}
