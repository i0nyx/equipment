package by.onyx.web.controller.admin.rest;

import by.onyx.common.pojo.Support;
import by.onyx.common.data.SupportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_APPLICATION;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_APPLICATION)
public class AdminRestApplication {

    @Autowired
    private SupportData supportData;

    @RequestMapping(value = "/select-by-id", method = RequestMethod.POST)
    public void selectById(@RequestBody int id, HttpServletResponse resp){
        Support result;
        if(id > 0){
            result = supportData.getById(id);
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
}
