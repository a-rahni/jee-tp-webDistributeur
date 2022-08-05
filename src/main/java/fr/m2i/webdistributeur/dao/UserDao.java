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
        for(User u:userDb.getUsers()){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
    
    public User findByEmail(String email){
        for(User u:userDb.getUsers()){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    // doit retourner une list ??  plusieur user peuvent avoir le même role!!!
    public User findByRole(Role role) {
        for(User u:userDb.getUsers()){
            if(u.getRole().equals(role)){
                return u;
            }
        }
        return null;
    }

    public User create(User user) {
        return userDb.addUser(user);
    }

    public User update(User user) {
        return userDb.updateUser(user);
    }

    public boolean delete(User user) {
        return false;
    }
}
