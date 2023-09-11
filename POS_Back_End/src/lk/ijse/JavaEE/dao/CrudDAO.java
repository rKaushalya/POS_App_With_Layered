package lk.ijse.JavaEE.dao;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID,CONNECTION> extends SuperDAO{
    boolean add (T entity,CONNECTION connection) throws SQLException;

    boolean update(T entity,CONNECTION connection) throws SQLException;

    boolean delete(ID id,CONNECTION connection) throws SQLException;

    ArrayList<T> search(CONNECTION connection) throws SQLException;
}
