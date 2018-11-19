package by.onyx.common.data.impl;

import by.onyx.common.data.OrderData;
import by.onyx.common.pojo.Order;
import by.onyx.common.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class OrderDataImpl implements OrderData {
    private OrderRepository orderRepository;

    @Override
    public Order save(final Order order) {
        Optional.ofNullable(order).orElseThrow(() -> new IllegalArgumentException("Error: order should not be null!"));
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> allByIsStateFalse() {
        return orderRepository.findByIsStateFalse();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(final int id) {
        return orderRepository.findOne(id);
    }
}
