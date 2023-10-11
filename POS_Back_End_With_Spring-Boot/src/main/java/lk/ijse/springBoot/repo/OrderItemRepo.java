package lk.ijse.springBoot.repo;

import lk.ijse.springBoot.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItems,String> {
}
