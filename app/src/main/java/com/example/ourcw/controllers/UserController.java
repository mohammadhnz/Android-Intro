package com.example.ourcw.controllers;

import com.example.ourcw.controllers.Exceptions.LoginExceptions;
import com.example.ourcw.controllers.Exceptions.LoginOnceException;
import com.example.ourcw.models.Student;
import com.example.ourcw.models.Teacher;
import com.example.ourcw.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;


public class UserController {
    private static UserController userController;
    private User user;
    private ArrayList<Teacher> allTeachers = new ArrayList<>();
    private ArrayList<Student> allStudents = new ArrayList<>();

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
        if (this.user != null) {
            return "Error: You already logged in";
        }
        this.user = new Teacher(username, password, firstName, lastName, university);
        this.allTeachers.add((Teacher) this.user);
        this.saveAllTeachers();
        return "Successful!";
    }

    public String registerStudent(String username, String password, String firstName, String lastName, String studentId) {
        for (Student student : allStudents) {
            if (student.getUsername().equals(username.trim())) {
                return "Error: Student exists with this username";
            }
        }
        if (this.user != null) {
            return "Error: You already logged in";
        }
        this.user = new Student(username, password, firstName, lastName, studentId);
        this.allStudents.add((Student) this.user);
        this.saveAllStudents();
        return "Successful!";
    }

    public String logout() {
        if (this.user == null) {
            return "Error: You are not logged in.";
        }
        this.user = null;
        return "Logged out";
    }

    public User getCurrentUser() {
        if (this.user == null) {
            return null;
        }
        return this.user;
    }

    public void login(String username, String password) throws LoginOnceException, LoginExceptions {
        if (this.user != null) {
            throw new LoginOnceException(this.getCurrentUserType());
        }
        User currentUser = User.login(username, password);
        if (currentUser == null) {
            throw new LoginExceptions();
        }
        this.user = currentUser;
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

    public void resetAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(allTeachers);
        users.addAll(allStudents);
        User.setUsers(users);
        Teacher.setTeachers(allTeachers);
        Student.setStudents(allStudents);
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
