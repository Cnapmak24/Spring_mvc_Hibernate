package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    User getUserById(Long id);

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(User user);
}
