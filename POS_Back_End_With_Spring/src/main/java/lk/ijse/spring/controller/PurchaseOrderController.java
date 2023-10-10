package lk.ijse.spring.controller;

import lk.ijse.spring.dto.PurchaseOrderDTO;
import lk.ijse.spring.services.PurchaseOrderService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/purchase_order")
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderService service;

    @PostMapping
    public ResponseUtil purchaseOrder(@RequestBody PurchaseOrderDTO dto){
        service.purchaseOrder(dto);
        return new ResponseUtil("OK","Order Purchase Success.!",dto);
    }
}
