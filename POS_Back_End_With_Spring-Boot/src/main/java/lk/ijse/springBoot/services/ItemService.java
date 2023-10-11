package lk.ijse.springBoot.services;

import lk.ijse.springBoot.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void addItem(ItemDTO dto);

    void deleteItem(String code);

    List<ItemDTO> getAllItem();

    ItemDTO findItem(String code);

    void updateItem(ItemDTO dto);
}
