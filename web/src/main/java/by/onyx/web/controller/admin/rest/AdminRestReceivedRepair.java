package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.pojo.Support;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_RECEIVED_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_RECEIVED_REPAIR)
public class AdminRestReceivedRepair {

    public static final Logger log = LoggerFactory.getLogger(AdminRestReceivedRepair.class);

    @Autowired
    private ReceivedRepairData receivedRepairData;
    @Autowired
    private SupportData supportData;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveReceivedRepair(@RequestBody ReceivedRepair receivedRepair){
        ReceivedRepair saved = null;
        if(receivedRepair != null){
            Date d = receivedRepair.getDate();
            if(d == null){
                receivedRepair.setDate(new Date());
            }
            saved = receivedRepairData.save(receivedRepair);
        }
        if(saved != null){
            if(saved.getSupport() != null) {
                Support support = supportData.getById(saved.getSupport().getId());
                support.setSupportStatus(Support.SupportStatus.PROCESSING);
                supportData.save(support);
            }
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public void getById(@RequestBody int id, HttpServletResponse resp){
        ReceivedRepair result;
        if(id != 0){
            result = receivedRepairData.getById(id);
            ObjectMapper mapper = new ObjectMapper();
            try{
                PrintWriter out = resp.getWriter();
                String json = mapper.writeValueAsString(result);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(json);
                out.flush();
            }catch (Exception e){
                log.error("cant send json " + e);
            }

        }
    }

}
