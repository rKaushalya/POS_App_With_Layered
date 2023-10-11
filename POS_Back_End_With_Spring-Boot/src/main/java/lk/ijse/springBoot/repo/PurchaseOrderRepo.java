package lk.ijse.springBoot.repo;

import lk.ijse.springBoot.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<OrderDetails,String> {
    OrderDetails findByOId(String oid);
}
