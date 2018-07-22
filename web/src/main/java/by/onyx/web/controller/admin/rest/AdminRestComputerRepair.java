package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.ComputerRepairData;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.ComputerRepair;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_COMPUTER_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_COMPUTER_REPAIR)
@AllArgsConstructor
public class AdminRestComputerRepair {

    private ComputerRepairData computerRepairData;
    private SupportData supportData;
    private ReceivedRepairData receivedRepairData;

    @PostMapping(value = "/save")
    public boolean saveComputerRepair(@RequestBody ComputerRepair data) {
        ComputerRepair result = null;
        boolean valid = true;
        if (data != null) {
            data.setDate(new Date());
            result = computerRepairData.save(data);
        }
        if (result != null) {
            Support support = result.getReceivedRepair().getSupport();
            if (support != null) {
                support.setSupportStatus(SupportStatus.FULFILLED);
                supportData.save(support);
            }
            ReceivedRepair receivedRepair = result.getReceivedRepair();
            receivedRepair.setState(true);
            receivedRepairData.save(receivedRepair);
        } else {
            valid = false;
        }
        return valid;
    }

}
