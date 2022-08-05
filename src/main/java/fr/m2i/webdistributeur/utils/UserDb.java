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
            System.out.println("Les identifiants pour accéder à la base de données sont erronés");
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
    
    public User addUser(User user){
        try{
        users.add(user);
        return users.get(users.size()-1);
        }catch(Exception e){
            return null;
        }
    }
    
    public User updateUser(User user){
        for(User u:users){
            if(u.getId() == user.getId()){
                u.setPassword(user.getPassword());
                u.setEmail(user.getEmail());
                u.setRole(user.getRole());
                u.setCredit(user.getCredit());
                return u;
            }            
        }
        return null;
    }
    
    public boolean deleteUser(User user){
        for(int i = 0; i<users.size();i++){
            if(users.get(i).getId() == user.getId()){
                users.remove(i);
                return true;
            }            
        }
        return false;
    }
}
