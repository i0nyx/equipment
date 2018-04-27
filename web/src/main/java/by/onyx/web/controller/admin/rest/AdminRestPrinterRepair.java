package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.PrinterRepairData;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.PrinterRepair;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_PRINTER_REPAIR;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_PRINTER_REPAIR)
public class AdminRestPrinterRepair {

    @Autowired
    private PrinterRepairData printerRepairData;
    @Autowired
    private SupportData supportData;
    @Autowired
    private ReceivedRepairData receivedRepairData;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean savePrinterRepairObject(@RequestBody PrinterRepair data){
        PrinterRepair result = null;
        boolean valid = true;
        if(data != null){
            data.setDate(new Date());
            result = printerRepairData.save(data);
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
