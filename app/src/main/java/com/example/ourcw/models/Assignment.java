package com.example.ourcw.models;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private String answer;
    private String question;
    private Teacher teacherName;
    private double score;
    private boolean alreadyHasAnswer;

    public Assignment(String assignmentId, String assignmentName) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.alreadyHasAnswer = false;
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

    public void setAlreadyHasAnswer(boolean alreadyHasAnswer) {
        this.alreadyHasAnswer = alreadyHasAnswer;
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

}
