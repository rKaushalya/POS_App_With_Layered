package lk.ijse.JavaEE.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    String id;
    String name;
    String address;
    double salary;
}
