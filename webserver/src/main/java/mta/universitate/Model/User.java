package mta.universitate.Model;


import mta.universitate.Utils.Hasher;

import java.util.Locale;

public class User{
    private int id;
    private String username;
    private String password;
    private Role role;

    public static User fromDB(int id)
    {
        User U = new User();
        U.id = id;

        return Database.getInstance().get(U);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Hasher.getHash(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
