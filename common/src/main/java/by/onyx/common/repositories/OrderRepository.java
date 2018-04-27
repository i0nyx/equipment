package by.onyx.common.repositories;

import by.onyx.common.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByIsStateFalse();
}
