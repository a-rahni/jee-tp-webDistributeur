package fr.m2i.webdistributeur.utils;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserDb {

    private static UserDb _instance;
    private static String _dbUser = "root";
    private static String _dbPass = "root";

    private List<User> users;

    private UserDb() {
        
        users = new ArrayList();
        users.add(new User("admin@admin.com","admin",Role.ADMIN));
        users.add(new User("user@user.com","user",Role.USER));
        users.add(new User("provider@provider.com","provider",Role.PROVIDER));
     
    }
    /** 
     * ???????????????????????????????????????????? 
     */
    // TO BE DTELETED when UserDBidentification is developed
    public static UserDb getInstance(){
        if (_instance == null) {
            _instance = new UserDb();
        }
        return _instance;
    }
   /*********************************************************/
    
    public static UserDb getInstance(String dbUser, String dbPass) {
        if (!_dbUser.equals(dbUser) || !_dbPass.equals(dbPass)) {
            return null;
        }

        if (_instance == null) {
            _instance = new UserDb();
        }

        return _instance;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public User checkUser(String email, String password) {

        if (email == null || password == null) {
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
