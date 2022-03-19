package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ourcw.HelperObjects.AssignmentsRecyclerViewAdapter;
import com.example.ourcw.HelperObjects.ClassesRecyclerViewAdapter;
import com.example.ourcw.models.Assignment;
import com.example.ourcw.models.Classroom;
import com.example.ourcw.models.Student;

import java.util.ArrayList;

public class ClassroomActivity extends AppCompatActivity implements AssignmentsRecyclerViewAdapter.OnNoteListener{

    RecyclerView assignmentsRecyclerView;
    ImageButton closeStudentClassroomPage;
    AssignmentsRecyclerViewAdapter adapter;
    TextView className;
    TextView teacherName;

    Student student;
    Classroom classroom;
    ArrayList<Assignment> assignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        Intent intent = getIntent();
        closeStudentClassroomPage = findViewById(R.id.closeClassroomPageButton);
        closeStudentClassroomPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                finish();
            }
        });

        assignmentsRecyclerView = findViewById(R.id.assignmentsRecyclerId);
        className = findViewById(R.id.classroomNameId);
        teacherName = findViewById(R.id.teacherOfClassNameTextId);

        String studentId = intent.getStringExtra("studentId");
        student = Student.getStudentById(studentId);
        String classroomId = intent.getStringExtra("classId");
        classroom = student.getUserClassById(classroomId);
        assignments = classroom.getAssignments();

        teacherName.setText(classroom.getTeacherUsername());
        className.setText(classroom.getClassName());

        assignmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        assert student != null;
        adapter = new AssignmentsRecyclerViewAdapter(this, assignments, ClassroomActivity.this);
        assignmentsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onNoteClickGoAssignmentPage(int position) {
        Intent intent = new Intent(this, StudentAssignmentPageActivity.class);
        intent.putExtra("studentId", student.getStudentID());
        intent.putExtra("classId", classroom.getClassId());
        intent.putExtra("assignmentId", assignments.get(position).getAssignmentId());
        startActivity(intent);
    }
}