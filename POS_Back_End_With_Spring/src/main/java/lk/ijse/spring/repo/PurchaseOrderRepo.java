package lk.ijse.spring.repo;

import lk.ijse.spring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<OrderDetails,String> {
    OrderDetails findByOId(String oid);
}
