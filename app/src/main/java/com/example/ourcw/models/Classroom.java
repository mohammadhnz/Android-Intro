package com.example.ourcw.models;

import java.util.ArrayList;

public class Classroom {
    private String classId;
    private String className;
    private String teacherUsername;
    private ArrayList<Assignment> assignments = new ArrayList<>();
    public static ArrayList<Classroom> classrooms = new ArrayList<>();

    public Classroom(String classId, String className, String teacherUsername) {
        this.className = className;
        this.classId = classId;
        this.teacherUsername = teacherUsername;
        classrooms.add(this);
    }

    public String getClassId() {
        return classId;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignmentToClass(Assignment assignment){
        assignments.add(assignment);
    }


    public static Classroom getClassByIdFromALlClasses(String classId){
        for (Classroom classroom : classrooms) {
            if (classroom.getClassId().equals(classId)){
                return classroom;
            }
        }
        return null;
    }

    public static boolean checkIfClassExist(String classId){
        for (Classroom classroom : classrooms) {
            if (classroom.getClassId().equals(classId)){
                return true;
            }
        }
        return false;
    }

    public Assignment getAssignmentOfClassById(String assignmentId){
        for (Assignment assignment : assignments) {
            if (assignment.getAssignmentId().equals(assignmentId)){
                return assignment;
            }
        }
        return null;
    }

    public boolean checkIfClassHasThisAssignment(String assignmentId){
        for (Assignment assignment : assignments) {
            if (assignment.getAssignmentId().equals(assignmentId)){
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacherOfClassWithUsername(String teacherUsername){
        return Teacher.getTeacherByUsername(teacherUsername);
    }


}
