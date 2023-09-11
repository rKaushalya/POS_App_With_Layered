package lk.ijse.JavaEE.bo.custom.impl;

import lk.ijse.JavaEE.bo.custom.PurchaseOrderBO;
import lk.ijse.JavaEE.dao.DAOFactory;
import lk.ijse.JavaEE.dao.custom.PurchaseOrderDAO;
import lk.ijse.JavaEE.dto.OrderItemDTO;
import lk.ijse.JavaEE.dto.PurchaseOrderDTO;
import lk.ijse.JavaEE.entity.PurchaseOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    private final PurchaseOrderDAO orderDAO = (PurchaseOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PURCHASEORDER);

    @Override
    public PurchaseOrderDTO searchOrder(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean purchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Connection connection) throws SQLException {
        connection.setAutoCommit(false);

        boolean isAdded = orderDAO.add(new PurchaseOrder(purchaseOrderDTO.getOId(), purchaseOrderDTO.getCash(), purchaseOrderDTO.getBalance(),
                purchaseOrderDTO.getDate(), purchaseOrderDTO.getCusId()), connection);

        if (!isAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        } else {
            ArrayList<OrderItemDTO> orderItem = purchaseOrderDTO.getOrderItem();
            for (OrderItemDTO dto : orderItem) {
                boolean isUpdate = orderDAO.updateItem(dto, connection);

                if (!isUpdate) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                boolean isOrderItemAdded = orderDAO.addOrderItem(purchaseOrderDTO.getOId(), dto, connection);

                if (!isOrderItemAdded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
    }
}
