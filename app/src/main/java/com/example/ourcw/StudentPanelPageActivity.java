package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ourcw.HelperObjects.ClassesRecyclerViewAdapter;
import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Student;

import java.util.ArrayList;

public class StudentPanelPageActivity extends AppCompatActivity implements ClassesRecyclerViewAdapter.OnNoteListener{

    RecyclerView classesRecyclerView;
    RecyclerView newClassesRecyclerView;
    ImageButton closeStudentPanelBtn;
    Button addNewClassBtn;
    ClassesRecyclerViewAdapter adapter;
    ClassesRecyclerViewAdapter newAdapter;

    Student student;
    ArrayList<Classroom> studentClassrooms;
    ArrayList<Classroom> newClassrooms;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pannel_page);

        Intent intent = getIntent();
        closeStudentPanelBtn = findViewById(R.id.closeStudentPannelBtnId);
        closeStudentPanelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

        classesRecyclerView = findViewById(R.id.studentClassesRecyclerId);
        newClassesRecyclerView = findViewById(R.id.newClassesRecyclerId);
        addNewClassBtn = findViewById(R.id.addNewClassBtnId);

        student = (Student) UserController.getInstance().getCurrentUser();
        assert student != null;
        studentClassrooms = student.getClassrooms();
        newClassrooms = student.classesStudentDoesNotHave();

        classesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        assert student != null;
        adapter = new ClassesRecyclerViewAdapter(this, studentClassrooms, StudentPanelPageActivity.this, true);
        classesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addNewClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newAdapter = new ClassesRecyclerViewAdapter(StudentPanelPageActivity.this, newClassrooms, StudentPanelPageActivity.this, false);
                newClassesRecyclerView = findViewById(R.id.newClassesRecyclerId);
                newClassesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                newClassesRecyclerView.setAdapter(newAdapter);
                newAdapter.notifyDataSetChanged();
            }
        });

    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onNoteClickAddClass(int position) {
        Classroom classroom = newClassrooms.get(position);
        student.joinClass(classroom.getClassId());
        studentClassrooms = student.getClassrooms();
        adapter.notifyDataSetChanged();
        newClassrooms.remove(classroom);
        newAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClickShowClassPage(int position) {
        Classroom classroom = studentClassrooms.get(position);
        Intent intent = new Intent(this, ClassroomActivity.class);
        intent.putExtra("studentId", student.getStudentID());
        intent.putExtra("classId", classroom.getClassId());
        startActivity(intent);
    }
}