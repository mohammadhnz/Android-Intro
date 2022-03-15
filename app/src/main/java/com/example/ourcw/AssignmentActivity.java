package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AssignmentActivity extends AppCompatActivity {

    private ImageButton closeAssignmentBtn;
    private Button submitAnsBtn;
    private Button deleteAnsBtn;
    private Button editAnsBtn;
    private TextView assignmentAnswer;
    private EditText assignmentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
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

        submitAnsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = assignmentAnswer.getText().toString();

            }
        });
    }
}