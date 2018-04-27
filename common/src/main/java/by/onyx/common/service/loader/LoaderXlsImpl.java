package by.onyx.common.service.loader;

import by.onyx.common.data.EquipmentData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.onyx.common.constants.ConstantsLoader.FILE_PATH;

@Service
public class LoaderXlsImpl implements LoaderXls{

    private static final Logger log = LoggerFactory.getLogger(LoaderXls.class);

    @Autowired
    private EquipmentData equipmentData;

    public OutputStream generateXlsFile(OutputStream out) {
        try{
            XSSFWorkbook workbook = new XSSFWorkbook();
            ExportToExcelService export = new ExportToExcelService(equipmentData);
            export.processEquipmentSheer(workbook);

            workbook.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void uploadXlsFile(MultipartFile multipartFile) {
        DateFormat df = new SimpleDateFormat("dd.mm.yyyy-hh.mm");
        String fileName = df.format(new Date()) + "_" +  multipartFile.getOriginalFilename() ;
        File file = new File(FILE_PATH, fileName);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            if(file != null){
                FileInputStream inputStream = new FileInputStream(file);
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                ImportToExcelService importService = new ImportToExcelService(equipmentData);
                importService.processEquipmentSheet(workbook);
            }
        }catch (Exception e){
            log.error("don't upload file or update bd " + e);
        }
    }
}
