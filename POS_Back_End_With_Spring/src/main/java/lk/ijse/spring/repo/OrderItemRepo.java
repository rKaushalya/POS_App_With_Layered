package lk.ijse.spring.repo;

import lk.ijse.spring.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItems,String> {
}
