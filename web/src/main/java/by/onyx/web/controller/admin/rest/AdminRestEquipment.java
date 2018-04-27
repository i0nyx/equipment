package by.onyx.web.controller.admin.rest;

import by.onyx.common.pojo.Equipment;
import by.onyx.common.data.EquipmentData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_EQUIPMENT;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_EQUIPMENT)
public class AdminRestEquipment {

    @Autowired
    private EquipmentData equipmentData;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveEquipment(@RequestBody Equipment equipment){
        Equipment result = null;
        if(equipment != null){
            result = equipmentData.save(equipment);
        }
        if(result != null){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/check-code", method = RequestMethod.POST)
    public void checkNumber(@RequestBody String code, HttpServletResponse resp){
        Equipment result;
        result = equipmentData.getByCode(code);
        if(result != null){
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                PrintWriter out = resp.getWriter();
                String json = objectMapper.writeValueAsString(result);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(json);
                out.flush();
            }catch (Exception e){
                System.out.println("error convert to json " + e);
            }
        }

    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteEquipment(@RequestBody int id){
        equipmentData.deleteById(id);
    }



}
