package com.example.ourcw.models;

import java.util.ArrayList;

public class Classroom {
    private String className;
    private Teacher teacher;
    private ArrayList<Assignment> assignments = new ArrayList<>();
    private ArrayList<Student> studentsInClass = new ArrayList<>();
    public static ArrayList<Classroom> classrooms = new ArrayList<>();

    public Classroom(String className, Teacher teacher) {
        this.className = className;
        this.teacher = teacher;
        classrooms.add(this);
    }
}
