package com.example.ourcw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.Student;

public class TeacherRegistrationActivity extends AppCompatActivity {

    private Button backButton;
    private Button registerButton;

    private EditText username;
    private EditText firstname;
    private EditText lastname;
    private EditText password;
    private EditText universityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        Intent intent = getIntent();
        backButton = findViewById(R.id.teacherBackLoginButtonId);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                setEmptyTextEditors();
                finish();
            }
        });
        username = findViewById(R.id.teacherUsernameID);
        password = findViewById(R.id.teacherPasswordId);
        firstname = findViewById(R.id.teacherFirstNameId);
        lastname = findViewById(R.id.teacherLastNameId);
        universityID = findViewById(R.id.universityID);

        registerButton = findViewById(R.id.teacherRegisterButtonID);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUsername = username.getText().toString();
                String sPassword = password.getText().toString();
                String sFirstname = firstname.getText().toString();
                String sLastname = lastname.getText().toString();
                String sUniversityID = universityID.getText().toString();
                if (!isThereEmptyInput(sUsername, sPassword, sFirstname, sLastname, sUniversityID)) {
                    if (Student.is_username_valid(username.getText().toString())) {
                        String response = UserController.getInstance().registerTeacher(
                                sUsername,
                                sPassword,
                                sFirstname,
                                sLastname,
                                sUniversityID
                        );
                        Intent intent1 = new Intent();
                        setResult(100, intent1);
                        setEmptyTextEditors();
                        finish();
                    } else {
                        createToast("repeated username");
                    }
                } else {
                    createToast("no empty input");
                }
            }
        });
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(
                TeacherRegistrationActivity.this,
                message,
                Toast.LENGTH_LONG
        );
        toast.show();
    }

    private boolean isThereEmptyInput(String username, String password, String firstname, String lastname, String studentID) {
        return username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || studentID.isEmpty();
    }

    private void setEmptyTextEditors(){
        username = findViewById(R.id.teacherUsernameID);
        password = findViewById(R.id.teacherPasswordId);
        firstname = findViewById(R.id.teacherFirstNameId);
        lastname = findViewById(R.id.teacherLastNameId);
        universityID = findViewById(R.id.universityID);
        String emptyString = "";
        username.setText(emptyString);
        password.setText(emptyString);
        firstname.setText(emptyString);
        lastname.setText(emptyString);
        universityID.setText(emptyString);
    }
}