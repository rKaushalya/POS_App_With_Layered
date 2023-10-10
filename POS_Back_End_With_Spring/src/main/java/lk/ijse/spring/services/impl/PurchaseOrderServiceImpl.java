package lk.ijse.spring.services.impl;

import lk.ijse.spring.dto.OrderItemsDTO;
import lk.ijse.spring.dto.PurchaseOrderDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.OrderDetails;
import lk.ijse.spring.entity.OrderItems;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderItemRepo;
import lk.ijse.spring.repo.PurchaseOrderRepo;
import lk.ijse.spring.services.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    PurchaseOrderRepo order;

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    CustomerRepo cusRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void purchaseOrder(PurchaseOrderDTO dto) {
        Customer customer = cusRepo.findByCusId(dto.getCusId());

        order.save(new OrderDetails(dto.getOId(),dto.getCash(),dto.getBalance(),dto.getDate(),dto.getCusId(),customer,dto.getOrderItem()));
        ArrayList<OrderItemsDTO> orderItem = dto.getOrderItem();

        for (OrderItemsDTO d : orderItem) {
            OrderDetails orderDetails = order.findByOId(dto.getOId());

            Item item = itemRepo.findByCode(d.getItemCode());
            int newQty = item.getQty()-d.getItemQty();
            itemRepo.updateItem(newQty,d.getItemCode());

            orderItemRepo.save(new OrderItems(dto.getOId(),d.getItemCode(),d.getItemQty(),orderDetails,item));
        }
    }

    @Override
    public PurchaseOrderDTO searchOrder() {
        return null;
    }
}
