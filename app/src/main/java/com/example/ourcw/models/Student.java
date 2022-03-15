package com.example.ourcw.models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private static List<Student> students = new ArrayList<>();
    private String studentID;


    public Student(String username, String password, String firstName, String lastName, String studentID) {
        super(username, password, firstName, lastName);
        this.studentID = studentID;
        students.add(this);
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

    public boolean checkIfAlreadyInClass(String classId){
        // first we check if class exists at all, if it does we check if student is already in class or not
        Classroom classroom = null;
        if (Classroom.checkIfClassExist(classId)){
            classroom = Classroom.getClassByIdFromALlClasses(classId);
        }
        else return false;
        return getClassrooms().contains(classroom) && classroom != null;
    }

    public void joinClass(String classId){
        if (Classroom.checkIfClassExist(classId) && !checkIfAlreadyInClass(classId)){
            //if class exist, we get it and student doesn't have it already, we
            //add the class to students classes list
            //we also add student to that class
            Classroom classroom = Classroom.getClassByIdFromALlClasses(classId);
            ArrayList<Classroom> usersClasses = getClassrooms();
            usersClasses.add(classroom);
            setClassrooms(usersClasses);
            assert classroom != null;
            classroom.addStudentToClass(this);
        }
    }

    public void submitAssignmentAnswer(String classId, String assignmentName, String answer){
        Classroom classroom = getUserClassById(classId);
        if (classroom.checkIfClassHasThisAssignment(assignmentName)){
            Assignment assignment = classroom.getAssignmentOfClassById(assignmentName);
            assignment.setAnswer(answer);
        }
    }

    public void deleteAssignmentAnswer(String classId, String assignmentId, String newAnswer){
        Classroom classroom = getUserClassById(classId);
        if (classroom.checkIfClassHasThisAssignment(assignmentId)){
            Assignment assignment = classroom.getAssignmentOfClassById(assignmentId);
            assignment.setAnswer("");
        }
    }

}
