package com.example.ourcw.models;

public class Assignment {
    private String assignmentName;
    private String question;
    private String teacherName;
    private double score;

    public Assignment(String assignmentName, String teacherName, String question) {
        this.assignmentName = assignmentName;
        this.teacherName = teacherName;
        this.question = question;
    }
}
