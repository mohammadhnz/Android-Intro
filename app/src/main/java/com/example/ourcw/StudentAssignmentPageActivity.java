package com.example.ourcw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Student;
import com.example.ourcw.models.Submission;

import java.util.Objects;

public class StudentAssignmentPageActivity extends AppCompatActivity {

    private ImageButton closeAssignmentBtn;
    private Button submitAnsBtn;
    private Button deleteAnsBtn;
    private Button editAnsBtn;
    private EditText assignmentAnswer;
    private EditText assignmentName;
    private EditText assignmentScore;
    private Submission submission;


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



        String assignmentId = intent.getStringExtra("assignmentId");
        String classroomId = intent.getStringExtra("classId");


        Student student = (Student) UserController.getInstance().getCurrentUser();
        assert student != null;
        Classroom classroom = student.getUserClassById(classroomId);
        Assignment assignment = classroom.getAssignmentOfClassById(assignmentId);

        assignmentName.setText(assignment.getAssignmentName());

        if (assignment.checkIfStudentSubmissionExists(student.getStudentID())){
            submission = assignment.getStudentSubmission(student.getStudentID());
            assignmentAnswer.setText(submission.getAnswer());
        }else assignmentAnswer.setText("");

        submitAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(StudentAssignmentPageActivity.this);
                alertSubmit.setTitle("Submitting Assignment");
                alertSubmit.setMessage("Are you sure you want to submit your answer?");
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(assignment.checkIfStudentSubmissionExists(student.getStudentID())){
                            submission = assignment.getStudentSubmission(student.getStudentID());
                            submission.setAnswer(assignmentAnswer.getText().toString());
                        }else{
                            submission = new Submission(student.getStudentID(), assignmentAnswer.getText().toString());
                            //assignment.getStudentSubmission(student.getStudentID()).setAnswer(assignmentAnswer.getText().toString());
                        }

                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "Answer submitted", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        assignmentAnswer.setText(assignment.getAnswer());
                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "Answer is not submitted", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.show();
            }
        });

        editAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertEdit = new AlertDialog.Builder(StudentAssignmentPageActivity.this);
                alertEdit.setTitle("Editing Assignment");
                alertEdit.setMessage("Are you sure you want to edit your answer?");
                alertEdit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(assignment.checkIfStudentSubmissionExists(student.getStudentID())){
                            submission = assignment.getStudentSubmission(student.getStudentID());
                            submission.setAnswer(assignmentAnswer.getText().toString());
                        }else{
                            submission = new Submission(student.getStudentID(), assignmentAnswer.getText().toString());
                            //assignment.getStudentSubmission(student.getStudentID()).setAnswer(assignmentAnswer.getText().toString());
                        }
                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "Answer edited", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertEdit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        assignmentAnswer.setText(assignment.getAnswer());
                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "Answer is not edited", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertEdit.show();
            }
        });

        deleteAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDelete = new AlertDialog.Builder(StudentAssignmentPageActivity.this);
                alertDelete.setTitle("Deleting Assignment Answer");
                alertDelete.setMessage("Are you sure you want to delete your answer?");
                alertDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(assignment.checkIfStudentSubmissionExists(student.getStudentID())){
                            submission = assignment.getStudentSubmission(student.getStudentID());
                            submission.setAnswer("");
                        }else{
                            submission = new Submission(student.getStudentID(), "");
                            //assignment.getStudentSubmission(student.getStudentID()).setAnswer("");
                        }
                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "assignment deleted", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(StudentAssignmentPageActivity.this, "not deleted", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertDelete.show();
            }
        });
        if (assignment.checkIfStudentSubmissionExists(student.getStudentID())){
            assignmentScore.setText(submission.getScore());
        }else{
            assignmentScore.setText("0");
        }
    }
}