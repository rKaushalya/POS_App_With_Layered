package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepo extends JpaRepository<Item,String> {
    Item findByCode(String code);

    /*@Query(value = "SELECT qty FROM item WHERE code=:code",nativeQuery = true)
    int getItemQty(@Param("code") String code);*/

    @Query(value = "UPDATE item SET qty=?1 WHERE code=?2",nativeQuery = true)
    void updateItem(int qty,String code);
}
