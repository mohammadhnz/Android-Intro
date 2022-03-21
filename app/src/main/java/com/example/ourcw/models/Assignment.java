package com.example.ourcw.models;

import androidx.annotation.NonNull;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private String answer;
    private String question;
    private Teacher teacherName;
    private double score;

    public Assignment(String assignmentId, String assignmentName) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.score = 0.0;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getScore() {
        return score;
    }

    public boolean isAlreadyHasAnswer(){
        return answer != null;
    }

    @NonNull
    @Override
    public String toString() {
        return this.assignmentName + "  " + this.assignmentId;
    }

}
