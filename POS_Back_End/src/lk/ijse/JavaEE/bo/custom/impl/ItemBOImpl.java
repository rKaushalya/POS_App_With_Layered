package lk.ijse.JavaEE.bo.custom.impl;

import lk.ijse.JavaEE.bo.custom.ItemBO;
import lk.ijse.JavaEE.dao.DAOFactory;
import lk.ijse.JavaEE.dao.custom.ItemDAO;
import lk.ijse.JavaEE.dto.ItemDTO;
import lk.ijse.JavaEE.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> searchItem(Connection connection) throws SQLException {
        ArrayList<Item> items = itemDAO.search(connection);
        ArrayList<ItemDTO> dtos = new ArrayList<>();
        for (Item i : items) {
            dtos.add(new ItemDTO(i.getCode(),i.getDescription(),i.getQty(),i.getPrice()));
        }
        return dtos;
    }

    @Override
    public boolean addItem(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.add(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice()),connection);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice()),connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws SQLException {
        return itemDAO.delete(id,connection);
    }
}
