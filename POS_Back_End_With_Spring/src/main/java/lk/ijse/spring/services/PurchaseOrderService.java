package lk.ijse.spring.services;

import lk.ijse.spring.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {
    void purchaseOrder(PurchaseOrderDTO dto);

    PurchaseOrderDTO searchOrder();
}
