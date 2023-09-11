package lk.ijse.JavaEE.bo.custom;

import lk.ijse.JavaEE.bo.SuperBO;
import lk.ijse.JavaEE.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> searchItem(Connection connection) throws SQLException;

    boolean addItem(ItemDTO itemDTO, Connection connection) throws SQLException;

    boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException;

    boolean deleteItem(String id, Connection connection) throws SQLException;
}
