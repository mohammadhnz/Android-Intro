package com.example.ourcw.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {
    private static List<Teacher> teachers = new ArrayList<>();
    private String university;

    public Teacher(String username, String password, String firstName, String lastName, String university) {
        super(username, password, firstName, lastName);
        this.university = university;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(List<Teacher> teachers) {
        Teacher.teachers = teachers;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
