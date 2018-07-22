package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.EquipmentData;
import by.onyx.common.pojo.Equipment;
import by.onyx.common.util.ObjectToJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_EQUIPMENT;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_EQUIPMENT)
@AllArgsConstructor
public class AdminRestEquipment {

    private EquipmentData equipmentData;

    @PostMapping(value = "/save")
    public boolean saveEquipment(@RequestBody Equipment equipment) {
        Equipment result = null;
        boolean valid = true;
        if (equipment != null) {
            result = equipmentData.save(equipment);
        }
        if (result == null) {
            valid = false;
        }
        return valid;
    }

    @PostMapping(value = "/check-code")
    public void checkNumber(@RequestBody String code, HttpServletResponse resp) {
        Equipment result;
        result = equipmentData.getByCode(code);
        if (result != null) {
            ObjectToJson.genObjectToJson(resp, result);
            /*ObjectMapper objectMapper = new ObjectMapper();
            try {
                PrintWriter out = resp.getWriter();
                String json = objectMapper.writeValueAsString(result);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(json);
                out.flush();
            } catch (Exception e) {
                System.out.println("error convert to json " + e);
            }*/
        }

    }

    @PostMapping(value = "/delete")
    public void deleteEquipment(@RequestBody int id) {
        equipmentData.deleteById(id);
    }


}
