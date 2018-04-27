package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.*;
import by.onyx.common.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static by.onyx.common.pojo.Support.SupportStatus.PENDING;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_MENU;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_MENU)
public class AdminRestMenu {

    @Autowired
    private SupportData supportData;
    @Autowired
    private ReceivedRepairData receivedRepairData;
    @Autowired
    private OrderData orderData;

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public int attentionSupport(){
        int count;
        List<Support> supportList = supportData.getAllByStatus(PENDING);
        count = supportList.size();
        return count;
    }
    @RequestMapping(value = "/received-repair", method = RequestMethod.POST)
    public int attentionReceivedRepair(){
        int count;
        List<ReceivedRepair> receivedRepairs = receivedRepairData.getByState(false);
        count = receivedRepairs.size();
        return count;
    }
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public int attentionOrder(){
        int count;
        List<Order> orders = orderData.allByIsStateFalse();
        count = orders.size();
        return count;
    }

}
