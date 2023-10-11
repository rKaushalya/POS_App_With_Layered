package lk.ijse.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseOrderDTO {
    String oId;
    double cash;
    double balance;
    Date date;
    String cusId;
    ArrayList<OrderItemsDTO> orderItem = new ArrayList<>();
}
