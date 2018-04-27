package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.CartridgeRepair;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.data.CartridgeRepairData;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_CARTRIDGE_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_CARTRIDGE_REPAIR)
public class AdminRestCartridgeRepair {

    @Autowired
    private CartridgeRepairData repairData;
    @Autowired
    private ReceivedRepairData receivedRepairData;
    @Autowired
    private SupportData supportData;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean save(@RequestBody CartridgeRepair data){
        CartridgeRepair cartridgeRepair = null;
        if(data != null) {
            data.setDate(new Date());
            cartridgeRepair = repairData.save(data);
        }
        if(cartridgeRepair != null){
            if(cartridgeRepair.getReceivedRepair().getSupport() != null) {
                Support support = supportData.getById(cartridgeRepair.getReceivedRepair().getSupport().getId());
                support.setSupportStatus(Support.SupportStatus.FULFILLED);
                supportData.save(support);
            }
            ReceivedRepair receivedRepair = cartridgeRepair.getReceivedRepair();
            receivedRepair.setState(true);
            receivedRepairData.save(receivedRepair);
            return true;
        }
        return false;
    }
}
