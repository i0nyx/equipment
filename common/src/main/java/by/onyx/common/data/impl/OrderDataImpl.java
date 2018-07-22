package by.onyx.common.data.impl;

import by.onyx.common.data.OrderData;
import by.onyx.common.pojo.Order;
import by.onyx.common.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class OrderDataImpl implements OrderData {

    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        Order result = null;
        if (order != null) {
            try {
                result = orderRepository.saveAndFlush(order);
            } catch (Exception e) {
                log.error("can't save order " + e);
            }
        }
        return result;
    }

    @Override
    public List<Order> allByIsStateFalse() {
        List<Order> result = null;
        try {
            result = orderRepository.findByIsStateFalse();
        } catch (Exception e) {
            log.error("can't get orders by isState " + e);
        }
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> result = null;
        try {
            result = orderRepository.findAll();
        } catch (Exception e) {
            log.error("can't get all orders " + e);
        }
        return result;
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findOne(id);
    }
}
