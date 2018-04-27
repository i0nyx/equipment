package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.ComputerRepairData;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.ComputerRepair;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_COMPUTER_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_COMPUTER_REPAIR)
public class AdminRestComputerRepair {

    @Autowired
    private ComputerRepairData computerRepairData;
    @Autowired
    private SupportData supportData;
    @Autowired
    private ReceivedRepairData receivedRepairData;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean saveComputerRepair(@RequestBody ComputerRepair data){
        ComputerRepair result = null;
        boolean valid = true;
        if(data != null){
            data.setDate(new Date());
            result = computerRepairData.save(data);
        }
        if(result != null){
            Support support = result.getReceivedRepair().getSupport();
            if(support != null){
                support.setSupportStatus(Support.SupportStatus.FULFILLED);
                supportData.save(support);
            }
            ReceivedRepair receivedRepair = result.getReceivedRepair();
            receivedRepair.setState(true);
            receivedRepairData.save(receivedRepair);
        }else{
            valid = false;
        }
        return valid;
    }

}
