
package com.example.ourcw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ourcw.HelperObjects.SubmissionsRecyclerViewAdaptor;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Submission;
import com.example.ourcw.models.Teacher;

import java.util.ArrayList;

public class TeacherAssignmentGrading extends AppCompatActivity implements SubmissionsRecyclerViewAdaptor.OnNoteListener {

    RecyclerView assignmentSubmissionRecycler;
    EditText studentId;
    EditText score;
    Button gradeStudentSubmission;
    ImageButton closeTeacherAssignmentGrading;
    SubmissionsRecyclerViewAdaptor adapter;

    Assignment assignment;
    ArrayList<Submission> submissions;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assignment_grading);

        Intent intent = getIntent();
        closeTeacherAssignmentGrading = findViewById(R.id.closeTeacherAssignmentGradingIconId);
        closeTeacherAssignmentGrading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

        studentId = findViewById(R.id.studentIdTextId);
        score = findViewById(R.id.scoreTextId);
        gradeStudentSubmission = findViewById(R.id.teacherGradeBtnId);
        String classroomsId = intent.getStringExtra("classId");
        String assignmentId = intent.getStringExtra("assignmentId");
        Classroom classroom = Classroom.getClassByIdFromALlClasses(classroomsId);
        assignment = classroom.getAssignmentOfClassById(assignmentId);
        submissions = assignment.getSubmissions();
        assignmentSubmissionRecycler = findViewById(R.id.submissionGradingRecyclerId);

        assignmentSubmissionRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SubmissionsRecyclerViewAdaptor(this, submissions, TeacherAssignmentGrading.this, true);
        adapter.setTextSizes(15);
        assignmentSubmissionRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        gradeStudentSubmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentId = TeacherAssignmentGrading.this.studentId.getText().toString();
                Integer score = Integer.parseInt(TeacherAssignmentGrading.this.score.getText().toString());
                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(TeacherAssignmentGrading.this);
                alertSubmit.setTitle("Grading submission");
                alertSubmit.setMessage("Do you want to grade submission for studentID:" + studentId + "and score : " + score.toString());
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (studentId.equals("") || !assignment.checkIfStudentSubmissionExists(studentId)) {
                            Toast toast = Toast.makeText(TeacherAssignmentGrading.this, "please enter valid studentID", Toast.LENGTH_LONG);
                            toast.show();
                            TeacherAssignmentGrading.this.studentId.setText("");
                            TeacherAssignmentGrading.this.score.setText("");
                        } else {
                            Toast toast = null;
                            assignment.gradeSubmission(studentId, score);
                            toast = Toast.makeText(TeacherAssignmentGrading.this, "grade done", Toast.LENGTH_LONG);
                            submissions = assignment.getSubmissions();
                            adapter.notifyDataSetChanged();
                            toast.show();
                            TeacherAssignmentGrading.this.studentId.setText("");
                            TeacherAssignmentGrading.this.score.setText("");
                        }
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TeacherAssignmentGrading.this.studentId.setText("");
                        TeacherAssignmentGrading.this.score.setText("");
                        Toast toast = Toast.makeText(TeacherAssignmentGrading.this, "no submission has been graded", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.show();
            }
        });

    }

    @Override
    public void onNoteClickAddClass(int position) {

    }

    @Override
    public void onNoteClickShowClassPage(int position) {
    }
}