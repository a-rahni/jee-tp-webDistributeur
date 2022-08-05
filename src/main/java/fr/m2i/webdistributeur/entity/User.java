package fr.m2i.webdistributeur.entity;

public class User {
    
    private static int futureId = 1;
    
    private int id;
    private String email;
    private String password;
    private Role role;
    private int credit;

    public User() {

    }

    public User(String email, String password, Role role, int credit) {
        this.id = futureId++;
        this.email = email;
        this.password = password;
        this.role = role;
        this.credit = credit;
    }
    
    public User(String email, String password, Role role) {
        this(email, password, role,0);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    
}
