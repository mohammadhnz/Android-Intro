package com.example.ourcw.controllers;

import com.example.ourcw.models.Student;
import com.example.ourcw.models.Teacher;
import com.example.ourcw.models.User;

import java.io.DataOutputStream;
import java.util.ArrayList;


public class UserController {
    private static UserController userController;
    private User user;
    private ArrayList<Teacher> allTeachers = new ArrayList<>();
    private ArrayList<Student> allStudents = new ArrayList<>();
    private static User currentUser;

    private UserController() {
        this.user = null;
    }

    public static UserController getInstance() {
        if (userController == null) {
            synchronized (UserController.class) {
                if (userController == null) {
                    userController = new UserController();
                }
            }
        }
        return userController;
    }

    public String registerTeacher(String username, String password, String firstName, String lastName, String university) {
        for (Teacher teacher : allTeachers) {
            if (teacher.getUsername() == username.trim()) {
                return "Error: Teacher exists with this username";
            }
        }
        if (currentUser != null) {
            return "Error: You already logged in";
        }
        currentUser = new Teacher(username, password, firstName, lastName, university);
        this.user = currentUser;
        return "Successful!";
    }

    public String registerStudent(String username, String password, String firstName, String lastName, String studentId) {
        for (Student student : allStudents) {
            if (student.getUsername() == username.trim()) {
                return "Error: Student exists with this username";
            }
        }
        if (currentUser != null) {
            return "Error: You already logged in";
        }
        currentUser = new Student(username, password, firstName, lastName, studentId);
        this.user = currentUser;
        return "Successful!";
    }

    public String logout() {
        if (currentUser == null) {
            return "Error: You are not logged in.";
        }
        currentUser = null;
        return "Logged out";
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            return null;
        }
        return currentUser;
    }

    public String login(String username, String password) {
        if (this.user != null) {
            return "Error! You already logged in as a " + this.getCurrentUserType();
        }
        User currentUser = User.login(username, password);
        if (currentUser == null) {
            return "Error! Login failed.";
        }
        this.user = currentUser;
        return "Successful!";
    }

    public String getCurrentUserType() {
        if (this.user == null) {
            return "Error! You are not logged in";
        }
        if (this.user instanceof Teacher) {
            return "Teacher";
        }
        return "Student";
    }
}
