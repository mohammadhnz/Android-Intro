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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourcw.HelperObjects.ClassesRecyclerViewAdapter;
import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Student;
import com.example.ourcw.models.Teacher;

import java.util.ArrayList;

public class TeacherPanelPageActivity extends AppCompatActivity implements ClassesRecyclerViewAdapter.OnNoteListener{

    RecyclerView teacherClassesRecycler;
    EditText newClassId;
    EditText newClassName;
    Button addNewClassButton;
    ImageButton closeTeacherPanelPage;
    ClassesRecyclerViewAdapter adapter;

    Teacher teacher;
    ArrayList<Classroom> classrooms;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel_page);

        Intent intent = getIntent();
        closeTeacherPanelPage = findViewById(R.id.closeTeacherPanelIconId);
        closeTeacherPanelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

        teacherClassesRecycler = findViewById(R.id.teachersClassesRecyclerId);
        newClassId = findViewById(R.id.newClassIdTextId);
        newClassName = findViewById(R.id.newClassNameTextId);
        addNewClassButton = findViewById(R.id.teacherAddNewClassBtnId);


        teacher = (Teacher) UserController.getInstance().getCurrentUser();
        assert teacher != null;
        classrooms = teacher.getClassrooms();

        teacherClassesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        assert teacher != null;
        adapter = new ClassesRecyclerViewAdapter(this, classrooms, TeacherPanelPageActivity.this, true);
        teacherClassesRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addNewClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String className = newClassName.getText().toString();
                String classId = newClassId.getText().toString();

                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(TeacherPanelPageActivity.this);
                alertSubmit.setTitle("Creating New Class");
                alertSubmit.setMessage("Do you want to create Class with name " + className + "and Id : " + classId);
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (classId.equals("") || Classroom.checkIfClassExist(classId)) {
                            Toast toast = Toast.makeText(TeacherPanelPageActivity.this, "please enter valid classId", Toast.LENGTH_LONG);
                            toast.show();
                        }else{
                            teacher.createClass(classId, className);
                            classrooms = teacher.getClassrooms();
                            adapter.notifyDataSetChanged();
                            Toast toast = Toast.makeText(TeacherPanelPageActivity.this, "class create", Toast.LENGTH_LONG);
                            toast.show();
                            newClassName.setText("");
                            newClassId.setText("");
                        }
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newClassId.setText("");
                        newClassName.setText("");
                        Toast toast = Toast.makeText(TeacherPanelPageActivity.this, "no class has been created", Toast.LENGTH_LONG);
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
        Classroom classroom = classrooms.get(position);
        Intent intent = new Intent(this, ClassroomActivity.class);
        intent.putExtra("teacherId", teacher.getUsername());
        intent.putExtra("classId", classroom.getClassId());
        startActivity(intent);
    }
}