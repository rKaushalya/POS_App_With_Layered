package lk.ijse.JavaEE.bo.custom;

import lk.ijse.JavaEE.bo.SuperBO;
import lk.ijse.JavaEE.dto.PurchaseOrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface PurchaseOrderBO extends SuperBO {
    PurchaseOrderDTO searchOrder(Connection connection) throws SQLException;

    boolean purchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Connection connection) throws SQLException;
}
