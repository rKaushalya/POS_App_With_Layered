package lk.ijse.JavaEE.dao.custom.impl;

import lk.ijse.JavaEE.dao.custom.PurchaseOrderDAO;
import lk.ijse.JavaEE.dto.OrderItemDTO;
import lk.ijse.JavaEE.entity.PurchaseOrder;
import lk.ijse.JavaEE.util.CrudUtl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {

    @Override
    public boolean add(PurchaseOrder entity, Connection connection) throws SQLException {
        return CrudUtl.execute("INSERT INTO orderDetails VALUES (?,?,?,?,?)",connection,entity.getOId(),entity.getCash(),
                entity.getBalance(),entity.getDate(),entity.getCusId());
    }

    @Override
    public boolean update(PurchaseOrder entity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean updateItem(OrderItemDTO dto, Connection connection) throws SQLException {
        ResultSet execute = CrudUtl.execute("SELECT qty FROM item WHERE code=?", connection,dto.getItemCode());
        int newValue = 0;
        while (execute.next()){
            int qtyOnHand = execute.getInt(1);
            newValue = qtyOnHand - dto.getItemQty();
        }

        return CrudUtl.execute("UPDATE item SET qty=? WHERE code=?",connection,newValue,dto.getItemCode());
    }

    @Override
    public boolean addOrderItem(String oId, OrderItemDTO dto, Connection connection) throws SQLException {
        return CrudUtl.execute("INSERT INTO orderItems VALUES (?,?,?)",connection,oId,dto.getItemCode(),dto.getItemQty());
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<PurchaseOrder> search(Connection connection) throws SQLException {
        return null;
    }
}
