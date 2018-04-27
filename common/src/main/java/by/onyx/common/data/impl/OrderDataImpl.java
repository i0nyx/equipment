package by.onyx.common.data.impl;

import by.onyx.common.data.OrderData;
import by.onyx.common.pojo.Order;
import by.onyx.common.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDataImpl implements OrderData {

    private static final Logger log = LoggerFactory.getLogger(OrderData.class);

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order save(Order order) {
        Order result = null;
        if(order != null){
            try {
                result = orderRepository.saveAndFlush(order);
            }catch (Exception e){
                log.error("can't save order " + e);
            }
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Order> allByIsStateFalse() {
        List<Order> result = null;
        try{
            result = orderRepository.findByIsStateFalse();
        }catch (Exception e){
            log.error("can't get orders by isState " + e);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        List<Order> result = null;
        try{
            result = orderRepository.findAll();
        }catch (Exception e){
            log.error("can't get all orders " + e);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(int id) {
        Order result = orderRepository.findOne(id);
        return result;
    }
}
