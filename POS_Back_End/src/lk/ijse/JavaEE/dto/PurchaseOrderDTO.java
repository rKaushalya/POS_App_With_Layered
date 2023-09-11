package lk.ijse.JavaEE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {
    String oId;
    double cash;
    double balance;
    Date date;
    String cusId;
    ArrayList orderItem = new ArrayList<>();


}
