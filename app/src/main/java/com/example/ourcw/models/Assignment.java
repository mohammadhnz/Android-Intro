package com.example.ourcw.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private String answer;
    private String question;
    private Teacher teacherName;
    private double score;
    private ArrayList<Submission> submissions;

    public Assignment(String assignmentId, String assignmentName, String question) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.submissions = new ArrayList<>();
        this.question = question;
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

    public boolean isAlreadyHasAnswer() {
        return answer != null;
    }

    public void addSubmission(String studentID, String answer) {
        for (Submission submission : this.submissions) {
            if (submission.getStudentID().equals(studentID)) {
                submission.setAnswer(answer);
                Classroom.saveAllClassrooms();
                return;
            }
        }
        this.submissions.add(new Submission(studentID, answer));
        Classroom.saveAllClassrooms();
    }

    public void updateSubmissionScore(String studentID, Integer score) {
        for (Submission submission : this.submissions) {
            if (submission.getStudentID().equals(studentID)) {
                submission.setScore(score);
                Classroom.saveAllClassrooms();
                return;
            }
        }
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    @Override
    public String toString() {
        return assignmentName + '\n' +
                "question: " + question;
    }

    public boolean checkIfStudentSubmissionExists(String studentId) {
        for (Submission submission : this.submissions) {
            if (submission.getStudentID().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    public void gradeSubmission(String studentId, Integer score) {
        for (Submission submission : this.submissions) {
            if (submission.getStudentID().equals(studentId)) {
                submission.setScore(score);
                Classroom.saveAllClassrooms();
                break;
            }
        }
    }

    public Submission getStudentSubmission(String studentId) {
        for (Submission submission : this.submissions) {
            if (submission.getStudentID().equals(studentId)) {
                return submission;
            }
        }
        return null;
    }
}
