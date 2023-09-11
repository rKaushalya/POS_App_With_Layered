package lk.ijse.JavaEE.dao.custom;

import lk.ijse.JavaEE.dao.CrudDAO;
import lk.ijse.JavaEE.dto.OrderItemDTO;
import lk.ijse.JavaEE.entity.PurchaseOrder;

import java.sql.Connection;
import java.sql.SQLException;

public interface PurchaseOrderDAO extends CrudDAO<PurchaseOrder, String, Connection> {
    boolean updateItem(OrderItemDTO dto, Connection connection) throws SQLException;

    boolean addOrderItem(String oId, OrderItemDTO dto, Connection connection) throws SQLException;
}
