
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

import com.example.ourcw.HelperObjects.TeacherClassPageAdapter;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Teacher;

import java.util.ArrayList;

public class TeacherClassAssignmentPage extends AppCompatActivity implements TeacherClassPageAdapter.OnNoteListener {

    RecyclerView classAssignmentsRecycler;
    EditText newAssignmentId;
    EditText newAssignmentName;
    EditText newAssignmentQuestion;
    Button teacherAddNewAssignmentButton;
    ImageButton closeClassAssignments;
    TeacherClassPageAdapter adapter;

    Teacher teacher;
    ArrayList<Assignment> assignments;
    Classroom classroom;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_page);

        Intent intent = getIntent();
        closeClassAssignments = findViewById(R.id.closeClassAssignmentsIconId);
        closeClassAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

        newAssignmentId = findViewById(R.id.newAssignmentIdTextId);
        newAssignmentName = findViewById(R.id.newAssignmentNameTextId);
        newAssignmentQuestion = findViewById(R.id.newAssignmentQuestionTextId);
        teacherAddNewAssignmentButton = findViewById(R.id.teacherAddNewAssignmentBtnId);
        String classroomsId = intent.getStringExtra("classId");
        classroom = Classroom.getClassByIdFromALlClasses(classroomsId);
        assignments = classroom.getAssignments();
        classAssignmentsRecycler = findViewById(R.id.classAssignmentsRecyclerId);

        classAssignmentsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new TeacherClassPageAdapter(this, assignments, TeacherClassAssignmentPage.this, true);
        adapter.setTextSizes(15);
        classAssignmentsRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        teacherAddNewAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String assignmentName = newAssignmentName.getText().toString();
                String assignmentID = newAssignmentId.getText().toString();
                String assignmentQuestion = newAssignmentQuestion.getText().toString();

                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(TeacherClassAssignmentPage.this);
                alertSubmit.setTitle("Creating New Assignment");
                alertSubmit.setMessage("Do you want to create Assignment with name " + assignmentName + "and Id : " + assignmentID);
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (assignmentID.equals("") || assignmentQuestion.equals("")) {
                            Toast toast = Toast.makeText(TeacherClassAssignmentPage.this, "please enter valid assignmentID and also assignmentQuestion", Toast.LENGTH_LONG);
                            toast.show();
                            newAssignmentId.setText("");
                            newAssignmentName.setText("");
                            newAssignmentQuestion.setText("");
                        } else {
                            Toast toast = null;
                            if (classroom.checkIfAssignmentIdExist(assignmentID)) {
                                classroom.updateAssignment(assignmentID,assignmentName);
                                toast = Toast.makeText(TeacherClassAssignmentPage.this, "assignment updates successfully", Toast.LENGTH_LONG);
                            }else {
                                classroom.createAssignment(assignmentID, assignmentName, assignmentQuestion);
                                toast = Toast.makeText(TeacherClassAssignmentPage.this, "assignment create", Toast.LENGTH_LONG);
                            }
                            assignments = classroom.getAssignments();
                            adapter.notifyDataSetChanged();
                            toast.show();
                            newAssignmentId.setText("");
                            newAssignmentName.setText("");
                            newAssignmentQuestion.setText("");
                        }
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newAssignmentId.setText("");
                        newAssignmentName.setText("");
                        newAssignmentQuestion.setText("");
                        Toast toast = Toast.makeText(TeacherClassAssignmentPage.this, "no class has been created", Toast.LENGTH_LONG);
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
        Assignment assignment = assignments.get(position);
        Intent intent = new Intent(this, TeacherAssignmentGrading.class);
        intent.putExtra("classId", classroom.getClassId());
        intent.putExtra("assignmentId", assignment.getAssignmentId());
        startActivity(intent);
    }
}