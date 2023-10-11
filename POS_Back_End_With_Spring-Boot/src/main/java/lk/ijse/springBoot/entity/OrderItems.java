package lk.ijse.springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItems {
    @Id
    private String oid;
    private String itemCode;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "oid",referencedColumnName = "oId",insertable = false,updatable = false)
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "itemCode",referencedColumnName = "code",insertable = false,updatable = false)
    private Item item;
}
