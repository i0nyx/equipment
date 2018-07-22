package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.OrderData;
import by.onyx.common.data.ReceivedRepairData;
import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.Order;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static by.onyx.common.pojo.SupportStatus.PENDING;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_MENU;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_MENU)
@AllArgsConstructor
public class AdminRestMenu {

    private SupportData supportData;
    private ReceivedRepairData receivedRepairData;
    private OrderData orderData;

    @PostMapping(value = "/application")
    public int attentionSupport() {
        int count;
        List<Support> supportList = supportData.getAllByStatus(PENDING);
        count = supportList.size();
        return count;
    }

    @PostMapping(value = "/received-repair")
    public int attentionReceivedRepair() {
        int count;
        List<ReceivedRepair> receivedRepairs = receivedRepairData.getByState(false);
        count = receivedRepairs.size();
        return count;
    }

    @PostMapping(value = "/order")
    public int attentionOrder() {
        int count;
        List<Order> orders = orderData.allByIsStateFalse();
        count = orders.size();
        return count;
    }

}
