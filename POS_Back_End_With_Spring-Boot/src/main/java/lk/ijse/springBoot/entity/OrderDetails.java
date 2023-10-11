package lk.ijse.springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    @Id
    private String oId;
    private double cash;
    private double balance;
    private Date date;
    private String cusId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cusId",referencedColumnName = "id",insertable = false,updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderDetails",cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
}
