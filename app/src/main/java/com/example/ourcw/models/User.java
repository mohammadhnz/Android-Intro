package com.example.ourcw.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static List<User> users = new ArrayList<>();
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isLogin;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isLogin = false;
        users.add(this);
    }

    public static boolean is_username_valid(String newUsername) {
        for (int i = 0; i < users.size(); i += 1) {
            if (users.get(i).username.equals(newUsername)) {
                return false;
            }
        }
        return true;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        User.users = users;
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
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public static User getUserByID(String username, String password) {
        for (int i = 0; i < users.size(); i += 1) {
            if (users.get(i).username.equals(username) && users.get(i).password.equals(password)) {
                return users.get(i);
            }
        }
        return null;
    }
}
