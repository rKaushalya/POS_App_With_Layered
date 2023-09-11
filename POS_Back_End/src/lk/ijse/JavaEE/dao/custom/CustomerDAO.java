package lk.ijse.JavaEE.dao.custom;

import lk.ijse.JavaEE.dao.CrudDAO;
import lk.ijse.JavaEE.entity.Customer;

import java.sql.Connection;

public interface CustomerDAO extends CrudDAO<Customer, String, Connection> {
}
