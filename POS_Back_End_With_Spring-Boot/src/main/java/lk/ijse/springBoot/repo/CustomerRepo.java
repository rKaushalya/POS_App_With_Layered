package lk.ijse.springBoot.repo;

import lk.ijse.springBoot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    Customer findByCusId(String id);
}
