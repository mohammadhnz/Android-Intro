package com.example.ourcw.models;

public class Submission {
    private String answer, studentID;
    private Integer score;

    public Submission(String studentID, String answer) {
        this.studentID = studentID;
        this.answer = answer;
        this.score = 0;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        this.score = 0;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Student: " + studentID + ", Score: " + score + "\n" +
                "answer: " + answer;
    }
}
