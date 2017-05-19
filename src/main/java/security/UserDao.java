package security;


import entity.InternalError;
import security.entity.Role;
import security.entity.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username, InternalError internalError);
    void addNewUser (User user, List<Role> roles, InternalError internalError);
}
