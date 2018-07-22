package by.onyx.web.controller.admin.nav;

import by.onyx.common.service.loader.LoaderXls;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;

import static by.onyx.common.constants.ConstantsLoader.UPLOAD_FILE_ERROR;
import static by.onyx.common.constants.ConstantsLoader.UPLOAD_FILE_SUCCESS;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_LOADER;

@Controller
@RequestMapping(value = CONST_URL_ADMIN_LOADER)
@AllArgsConstructor
public class AdminImportExportController {

    private LoaderXls loaderXls;

    @PostMapping(value = "/import-equipment", produces = {"application/json"})
    public @ResponseBody
    HashMap<String, String> importEquipment(MultipartHttpServletRequest request) {
        boolean valid = true;
        MultipartFile multipartFile = request.getFile("file");
        if (multipartFile != null) {
            loaderXls.uploadXlsFile(multipartFile);
        } else {
            valid = false;
        }
        HashMap<String, String> mapMsg = new HashMap<>();
        mapMsg.put("msg", String.valueOf(valid));
        if (valid) {
            mapMsg.put("msgSuccess", UPLOAD_FILE_SUCCESS);
        } else {
            mapMsg.put("msgError", UPLOAD_FILE_ERROR);
        }
        return mapMsg;
    }

    @GetMapping(value = "/download-equipment")
    public void getXlsFileEquipment(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=utf-8");
        response.setHeader("Content-Disposition,", "attachment; filename=equipment.xlsx");
        try {
            OutputStream out = response.getOutputStream();
            loaderXls.generateXlsFile(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
