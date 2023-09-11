package lk.ijse.JavaEE.bo.custom;

import lk.ijse.JavaEE.bo.SuperBO;
import lk.ijse.JavaEE.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> searchCustomer(Connection connection) throws SQLException;

    boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean deleteCustomer(String id, Connection connection) throws SQLException;
}
