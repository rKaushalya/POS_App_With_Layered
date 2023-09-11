package lk.ijse.JavaEE.dao.custom.impl;

import lk.ijse.JavaEE.dao.custom.ItemDAO;
import lk.ijse.JavaEE.entity.Item;
import lk.ijse.JavaEE.util.CrudUtl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item entity, Connection connection) throws SQLException {
        return CrudUtl.execute("insert into Item values(?,?,?,?)",connection,entity.getCode(),
                entity.getDescription(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean update(Item entity, Connection connection) throws SQLException {
        return CrudUtl.execute("UPDATE item SET name=?, qty=?, price=? WHERE code=?",connection,
                entity.getDescription(),entity.getQty(),entity.getPrice(),entity.getCode());
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return CrudUtl.execute("delete from Item where code=?",connection,id);
    }

    @Override
    public ArrayList<Item> search(Connection connection) throws SQLException {
        ResultSet execute = CrudUtl.execute("select * from Item", connection);
        ArrayList<Item> items = new ArrayList<>();
        while (execute.next()){
            String code = execute.getString(1);
            String description = execute.getString(2);
            int qty = execute.getInt(3);
            double price = execute.getDouble(4);
            items.add(new Item(code,description,qty,price));
        }
        return items;
    }
}
