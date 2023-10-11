package lk.ijse.springBoot.controller;
import lk.ijse.springBoot.dto.PurchaseOrderDTO;
import lk.ijse.springBoot.services.PurchaseOrderService;
import lk.ijse.springBoot.util.ResponseUtil;
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
