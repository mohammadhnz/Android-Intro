package com.example.ourcw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button navToRegistrationStudentButton;
    private Button navToRegistrationTeacherButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navToRegistrationStudentButton = findViewById(R.id.navToRegistrationStudentButtonId);
        navToRegistrationTeacherButton = findViewById(R.id.navToRegistrationTeacherButtonId);
        navToRegistrationTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        TeacherRegistrationActivity.class
                );
                startActivityForResult(
                        intent, 100
                );
            }
        });
        navToRegistrationStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        StudentRegistrationActivity.class
                );
                startActivityForResult(
                        intent, 100
                );
            }
        });
    }


}