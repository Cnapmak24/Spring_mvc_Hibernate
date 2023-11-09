package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Component
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        entityManager.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
    }


    @Transactional
    @Override
    public void update(Long id, User updateUser) {
        User userUpdate = entityManager.find(User.class, id);

        userUpdate.setName(updateUser.getName());
        userUpdate.setLastName(updateUser.getLastName());
        userUpdate.setAge(updateUser.getAge());
    }
}
