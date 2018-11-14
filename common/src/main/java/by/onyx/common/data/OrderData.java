package by.onyx.common.data;

import by.onyx.common.pojo.Order;

import java.util.List;

public interface OrderData {
    Order save(final Order order);
    List<Order> allByIsStateFalse();
    List<Order> getAllOrders();
    Order getOrderById(final int id);

}
