package lk.ijse.springBoot.services;

import lk.ijse.springBoot.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {
    void purchaseOrder(PurchaseOrderDTO dto);

    PurchaseOrderDTO searchOrder();
}
