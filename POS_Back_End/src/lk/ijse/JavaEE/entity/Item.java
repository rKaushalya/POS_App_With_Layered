package lk.ijse.JavaEE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    String code;
    String description;
    int qty;
    double price;
}
