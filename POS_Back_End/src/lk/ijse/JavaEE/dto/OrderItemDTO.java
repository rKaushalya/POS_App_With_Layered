package lk.ijse.JavaEE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {
    String itemCode;
    int itemQty;
}
