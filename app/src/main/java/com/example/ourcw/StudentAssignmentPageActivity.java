package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Student;

import java.util.Objects;

public class StudentAssignmentPageActivity extends AppCompatActivity {

    private ImageButton closeAssignmentBtn;
    private Button submitAnsBtn;
    private Button deleteAnsBtn;
    private Button editAnsBtn;
    private TextView assignmentAnswer;
    private EditText assignmentName;
    private EditText assignmentScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assignment_page);
        Intent intent = getIntent();
        closeAssignmentBtn = findViewById(R.id.closeAssignmentBtn);
        closeAssignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });
        submitAnsBtn = findViewById(R.id.submitAnsBtnId);
        deleteAnsBtn = findViewById(R.id.deleteAnsBtnId);
        editAnsBtn = findViewById(R.id.editAnsBtnId);
        assignmentAnswer = findViewById(R.id.assignmentAnswerId);
        assignmentName = findViewById(R.id.assignmentNameId);
        assignmentScore = findViewById(R.id.assignmentScoreId);


        String studentId = intent.getStringExtra("studentId");
        String assignmentId = intent.getStringExtra("assignmentId");
        String classroomId = intent.getStringExtra("classroomId");


        Student student = Student.getStudentById(studentId);
        assert student != null;
        Classroom classroom = student.getUserClassById(classroomId);
        Assignment assignment = classroom.getAssignmentOfClassById(assignmentId);

        assignmentName.setText(assignment.getAssignmentName());

        if (assignment.getAnswer() == null){
            assignmentAnswer.setText("");
        }else assignmentAnswer.setText(assignment.getAnswer());

        assignmentScore.setText(String.valueOf(assignment.getScore()));

        submitAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(Student.getStudentById(studentId)).submitAssignmentAnswer(
                        classroomId, assignmentId, assignmentAnswer.getText().toString());
            }
        });
        editAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(Student.getStudentById(studentId)).submitAssignmentAnswer(
                        classroomId, assignmentId, assignmentAnswer.getText().toString());
            }
        });

        deleteAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(Student.getStudentById(studentId)).deleteAssignmentAnswer(classroomId, assignmentId);
            }
        });
    }
}