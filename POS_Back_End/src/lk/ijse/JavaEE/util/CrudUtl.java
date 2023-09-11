package lk.ijse.JavaEE.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtl {
    public static<T> T execute(String sql, Connection connection, Object...args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject((i+1),args[i]);
        }

        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) preparedStatement.executeQuery();
        }
        return (T) (Boolean) (preparedStatement.executeUpdate() > 0);
    }
}
