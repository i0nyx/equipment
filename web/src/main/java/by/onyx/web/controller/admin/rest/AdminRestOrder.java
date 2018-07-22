package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.OrderData;
import by.onyx.common.pojo.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_ORDER;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_ORDER)
@AllArgsConstructor
public class AdminRestOrder {

    private OrderData orderData;

    @PostMapping(value = "/save")
    public void saveOrder(@RequestBody Order order) {
        if (order != null) {
            order.setStartDate(new Date());
            orderData.save(order);
        }
    }

    @PostMapping(value = "/order-success")
    public void orderSuccess(@RequestBody int id) {
        Order order = orderData.getOrderById(id);
        if (order != null) {
            order.setEndDate(new Date());
            order.setState(true);
            orderData.save(order);
        }
    }

}
