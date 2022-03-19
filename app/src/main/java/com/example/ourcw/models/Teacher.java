package com.example.ourcw.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher extends User {
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private String university;

    public Teacher(String username, String password, String firstName, String lastName, String university) {
        super(username, password, firstName, lastName);
        this.university = university;
        teachers.add(this);
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public static void setTeachers(ArrayList<Teacher> teachers) {
        Teacher.teachers = teachers;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public static Teacher getTeacherById(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(teacherId)) {
                return teacher;
            }
        }
        return null;
    }

    public void createClass(String classId, String className) {
        //first we check if class exist at all, then if it didn't we create a class and add it to teacher's classes
        if (!Classroom.checkIfClassExist(classId)) {
            ArrayList<Classroom> c = this.getClassrooms();
            c.add(new Classroom(classId, className, this.getUsername()));
            this.setClassrooms(c);
        }
    }
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    public void createAssignment(String assignmentId, String assignmentName, String classroomId) {
        //first we check if teacher has this class to create an assignment for; then we check if assignment does not already exist
        //then we get the class this teacher has and wants to create assignment for
        if (checkIfTeacherHasThisClass(getUserClassById(classroomId)) &&
                !getUserClassById(classroomId).checkIfClassHasThisAssignment(assignmentId)) {
            getUserClassById(classroomId).addAssignmentToClass(new Assignment(assignmentId, assignmentName));
        }
    }

    public void renameAssignment(String assignmentId, String classroomId, String assignmentNewName) {
        if (checkIfTeacherHasThisClass(getUserClassById(classroomId)) &&
                getUserClassById(classroomId).checkIfClassHasThisAssignment(assignmentId)) {
            Assignment assignment = getUserClassById(classroomId).getAssignmentOfClassById(assignmentId);
            assignment.setAssignmentName(assignmentNewName);
        }
    }

    public void scoreAStudentAssignment(String studentId, String classroomId, String assignmentId, double score) {
        //first we check if class exist, then we check if teacher has this class, then we check
        //if student with that id has that class and then we set a score for that student assignment
        if (Classroom.checkIfClassExist(classroomId)) {
            if (checkIfTeacherHasThisClass(Classroom.getClassByIdFromALlClasses(classroomId))) {
                if (Student.getStudentById(studentId).checkIfAlreadyInClass(classroomId)) {
                    Classroom classroom = Student.getStudentById(studentId).getUserClassById(classroomId);
                    Assignment assignment = classroom.getAssignmentOfClassById(assignmentId);
                    assignment.setScore(score);
                }
            }
        }
    }

    public String showStudentsAnswerForAnAssignment(String studentId, String classroomId, String assignmentId, double score) {
        String studentAnswer = "";
        if (Classroom.checkIfClassExist(classroomId)) {
            if (checkIfTeacherHasThisClass(Classroom.getClassByIdFromALlClasses(classroomId))) {
                if (Student.getStudentById(studentId).checkIfAlreadyInClass(classroomId)) {
                    //its this student classroom
                    Classroom classroom = Student.getStudentById(studentId).getUserClassById(classroomId);
                    //get this class's assignment with assignment's id
                    Assignment assignment = classroom.getAssignmentOfClassById(assignmentId);
                    studentAnswer = assignment.getAnswer();
                }
            }
        }
        return studentAnswer;
    }

    public boolean checkIfTeacherHasThisClass(Classroom classroom) {
        return getUserClassById(classroom.getClassId()) != null;
    }

    public static Teacher getTeacherByUsername(String teacherUserName) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(teacherUserName)) {
                return teacher;
            }
        }
        return null;
    }

}
