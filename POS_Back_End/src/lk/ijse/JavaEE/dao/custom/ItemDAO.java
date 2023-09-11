package lk.ijse.JavaEE.dao.custom;

import lk.ijse.JavaEE.dao.CrudDAO;
import lk.ijse.JavaEE.dao.SuperDAO;
import lk.ijse.JavaEE.entity.Item;

import java.sql.Connection;

public interface ItemDAO extends CrudDAO<Item, String, Connection> {
}
