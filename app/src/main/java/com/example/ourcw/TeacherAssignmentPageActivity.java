package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class TeacherAssignmentPageActivity extends AppCompatActivity {

    ImageButton exitPageBtn;
    EditText studentNameAndId;
    EditText assignmentName;
    TextView assignmentAnswer;
    EditText studentScore;
    Button submitScoreBtn;
    Button editScoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assignment_page);
        exitPageBtn = findViewById(R.id.closeAssignmentPageId);
        exitPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}