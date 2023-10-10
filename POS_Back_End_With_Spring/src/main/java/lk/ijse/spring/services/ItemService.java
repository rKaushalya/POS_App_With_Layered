package lk.ijse.spring.services;

import lk.ijse.spring.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO dto);

    void deleteItem(String code);

    List<ItemDTO> getAllItem();

    ItemDTO findItem(String code);

    void updateItem(ItemDTO dto);
}
