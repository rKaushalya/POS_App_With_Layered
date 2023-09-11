package lk.ijse.JavaEE.bo.custom.impl;

import lk.ijse.JavaEE.bo.custom.CustomerBO;
import lk.ijse.JavaEE.dao.DAOFactory;
import lk.ijse.JavaEE.dao.custom.CustomerDAO;
import lk.ijse.JavaEE.dto.CustomerDTO;
import lk.ijse.JavaEE.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> searchCustomer(Connection connection) throws SQLException {
        ArrayList<Customer> customers = customerDAO.search(connection);
        ArrayList<CustomerDTO> dtos = new ArrayList<>();
        for (Customer c : customers) {
            dtos.add(new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getSalary()));
        }
        return dtos;
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.add(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),
                customerDTO.getSalary()),connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException {
        return customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),
                customerDTO.getSalary()),connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws SQLException {
        return customerDAO.delete(id,connection);
    }
}
