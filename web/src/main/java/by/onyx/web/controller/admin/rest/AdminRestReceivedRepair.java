package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import by.onyx.common.util.ObjectToJson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_RECEIVED_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_RECEIVED_REPAIR)
@AllArgsConstructor
@Slf4j
public class AdminRestReceivedRepair {

    private ReceivedRepairData receivedRepairData;
    private SupportData supportData;

    @PostMapping(value = "/save")
    public boolean saveReceivedRepair(@RequestBody ReceivedRepair receivedRepair) {
        ReceivedRepair saved = null;
        if (receivedRepair != null) {
            Date d = receivedRepair.getDate();
            if (d == null) {
                receivedRepair.setDate(new Date());
            }
            saved = receivedRepairData.save(receivedRepair);
        }
        if (saved != null) {
            if (saved.getSupport() != null) {
                Support support = supportData.getById(saved.getSupport().getId());
                support.setSupportStatus(SupportStatus.PROCESSING);
                supportData.save(support);
            }
            return true;
        }
        return false;
    }

    @PostMapping(value = "/select")
    public void getById(@RequestBody int id, HttpServletResponse resp) {
        ReceivedRepair result;
        if (id != 0) {
            result = receivedRepairData.getById(id);
            ObjectToJson.genObjectToJson(resp, result);
            /*ObjectMapper mapper = new ObjectMapper();
            try {
                PrintWriter out = resp.getWriter();
                String json = mapper.writeValueAsString(result);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                out.print(json);
                out.flush();
            } catch (Exception e) {
                log.error("cant send json " + e);
            }*/

        }
    }

}
