package lk.ijse.JavaEE.dao.custom.impl;

import lk.ijse.JavaEE.dao.custom.CustomerDAO;
import lk.ijse.JavaEE.entity.Customer;
import lk.ijse.JavaEE.util.CrudUtl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer entity, Connection connection) throws SQLException {
        return CrudUtl.execute("insert into Customer values(?,?,?,?)",connection,
                entity.getId(),entity.getName(),entity.getAddress(),entity.getSalary());
    }

    @Override
    public boolean update(Customer entity, Connection connection) throws SQLException {
        return CrudUtl.execute("update Customer set name=?,address=?,salary=? where id=?",connection,
                entity.getName(),entity.getAddress(),entity.getSalary(),entity.getId());
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return CrudUtl.execute("delete from Customer where id=?",connection,id);
    }

    @Override
    public ArrayList<Customer> search(Connection connection) throws SQLException {
        ResultSet execute = CrudUtl.execute("select * from Customer", connection);
        ArrayList<Customer> customers = new ArrayList<>();
        while (execute.next()){
            String id = execute.getString(1);
            String name = execute.getString(2);
            String address = execute.getString(3);
            double salary = execute.getDouble(4);
            Customer customer = new Customer(id,name,address,salary);
            customers.add(customer);
        }
        return customers;
    }
}
