package security;

import error.Errors;
import security.entity.User;

import java.sql.SQLException;


//data Access Object - механизм доступа к объектам данных
public interface UserDao {
    User getUserByUsername(String username);
    void setNewUser(User user) throws SQLException;
}
