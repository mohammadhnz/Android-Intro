package com.example.ourcw.models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private static List<Student> students = new ArrayList<>();
    private String studentID;


    public Student(String username, String password, String firstName, String lastName, String studentID) {
        super(username, password, firstName, lastName);
        this.studentID = studentID;
    }
    public static boolean is_valid_student_id(String newStudentID){
        for (int i = 0; i < students.size(); i+=1){
            if (students.get(i).studentID.equals(newStudentID)){
                return false;
            }
        }
        return true;
    }
    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        Student.students = students;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}
