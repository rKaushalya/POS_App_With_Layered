package lk.ijse.JavaEE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrder {
    String oId;
    double cash;
    double balance;
    Date date;
    String cusId;
}
