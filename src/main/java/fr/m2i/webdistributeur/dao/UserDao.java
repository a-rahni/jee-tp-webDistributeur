package fr.m2i.webdistributeur.dao;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.utils.UserDb;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {

    private static Logger logger = Logger.getLogger(UserDao.class.getName());
    private UserDb userDb;

    public UserDao(UserDb userDb) {
        this.userDb = userDb;
    }

    public List<User> findAll() {
        return userDb.getUsers();
    }

    public User findById(int id) {
        return null;
    }

    public User findByRole(Role role) {
        return null;
    }

    public User create(User user) {
        return user;
    }

    public User update(User user) {
        return null;
    }

    public boolean delete(User user) {
        return false;
    }
}
