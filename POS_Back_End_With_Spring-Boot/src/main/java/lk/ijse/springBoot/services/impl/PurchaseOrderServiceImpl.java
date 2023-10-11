package lk.ijse.springBoot.services.impl;

import lk.ijse.springBoot.dto.OrderItemsDTO;
import lk.ijse.springBoot.dto.PurchaseOrderDTO;
import lk.ijse.springBoot.entity.Customer;
import lk.ijse.springBoot.entity.Item;
import lk.ijse.springBoot.entity.OrderDetails;
import lk.ijse.springBoot.entity.OrderItems;
import lk.ijse.springBoot.repo.CustomerRepo;
import lk.ijse.springBoot.repo.ItemRepo;
import lk.ijse.springBoot.repo.OrderItemRepo;
import lk.ijse.springBoot.repo.PurchaseOrderRepo;
import lk.ijse.springBoot.services.PurchaseOrderService;
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

//        order.save(new OrderDetails(dto.getOId(),dto.getCash(),dto.getBalance(),dto.getDate(),dto.getCusId(),customer,dto.getOrderItem()));
        order.save(mapper.map(dto, OrderDetails.class));
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
