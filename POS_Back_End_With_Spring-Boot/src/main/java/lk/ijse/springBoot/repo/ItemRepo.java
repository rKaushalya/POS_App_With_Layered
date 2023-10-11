package lk.ijse.springBoot.repo;

import lk.ijse.springBoot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item,String> {
    Item findByCode(String code);

    /*@Query(value = "SELECT qty FROM item WHERE code=:code",nativeQuery = true)
    int getItemQty(@Param("code") String code);*/

    @Query(value = "UPDATE item SET qty=?1 WHERE code=?2",nativeQuery = true)
    void updateItem(int qty,String code);
}
