package com.example.ourcw.controllers;

import com.example.ourcw.models.Student;
import com.example.ourcw.models.Teacher;
import com.example.ourcw.models.User;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.util.ArrayList;


public class UserController {
    private static UserController userController;
    private User user;
    private ArrayList<Teacher> allTeachers = new ArrayList<>();
    private ArrayList<Student> allStudents = new ArrayList<>();
    private static User currentUser;

    public static final String StudentDataKey = "all_students";
    public static final String TeacherDataKey = "all_teachers";

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
        this.allTeachers.add((Teacher) currentUser);
        this.saveAllTeachers();
        return "Successful!";
    }

    public String registerStudent(String username, String password, String firstName, String lastName, String studentId) {
        for (Student student : allStudents) {
            if (student.getUsername().equals(username.trim())) {
                return "Error: Student exists with this username";
            }
        }
        if (currentUser != null) {
            return "Error: You already logged in";
        }
        currentUser = new Student(username, password, firstName, lastName, studentId);
        this.allStudents.add((Student) currentUser);
        this.saveAllStudents();
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

    public void setAllTeachers(ArrayList<Teacher> allTeachers) {
        this.allTeachers = allTeachers;
    }

    public void setAllStudents(ArrayList<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<Teacher> getAllTeachers() {
        return allTeachers;
    }

    public ArrayList<Student> getAllStudents() {
        return allStudents;
    }
    public void saveAllStudents() {
        Database.getInstance().updateData(StudentDataKey, new Gson().toJson(allStudents));
    }

    public void saveAllTeachers() {
        Database.getInstance().updateData(TeacherDataKey, new Gson().toJson(allTeachers));
    }
}
