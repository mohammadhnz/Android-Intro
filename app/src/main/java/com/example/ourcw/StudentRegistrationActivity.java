package com.example.ourcw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.Student;

public class StudentRegistrationActivity extends AppCompatActivity {

    private Button backButton;
    private Button registerButton;

    private EditText username;
    private EditText firstname;
    private EditText lastname;
    private EditText password;
    private EditText studentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        Intent intent = getIntent();
        backButton = findViewById(R.id.studentBackLoginButtonId);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                setEmptyTextEditors();
                finish();
            }
        });
        // student data
        username = findViewById(R.id.studentUsernameID);
        password = findViewById(R.id.studentPasswordId);
        firstname = findViewById(R.id.studentFirstNameId);
        lastname = findViewById(R.id.studentLastNameId);
        studentID = findViewById(R.id.studentId);

        registerButton = findViewById(R.id.studentRegisterButtonID);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUsername = username.getText().toString();
                String sPassword = password.getText().toString();
                String sFirstname = firstname.getText().toString();
                String sLastname = lastname.getText().toString();
                String sStudentID = studentID.getText().toString();
                if (!isThereEmptyInput(sUsername, sPassword, sFirstname, sLastname, sStudentID)) {
                    if (
                            Student.is_valid_student_id(
                                    studentID.getText().toString()
                            )
                    ) {
                        if (Student.is_username_valid(username.getText().toString())) {
                            String response = UserController.getInstance().registerStudent(
                                    sUsername,
                                    sPassword,
                                    sFirstname,
                                    sLastname,
                                    sStudentID
                            );
                            createToast(response);
                            Intent intent1 = new Intent();
                            setResult(100, intent1);
                            setEmptyTextEditors();
                            finish();
                        } else {
                            createToast("repeated username");
                        }
                    } else {
                        createToast("repeated student id");
                    }
                } else {
                    createToast("no empty input");
                }
            }
        });
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(
                StudentRegistrationActivity.this,
                message,
                Toast.LENGTH_LONG
        );
        toast.show();
    }
    private boolean isThereEmptyInput(String username, String password, String firstname, String lastname, String studentID) {
        return username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || studentID.isEmpty();
    }

    private void setEmptyTextEditors(){
        username = findViewById(R.id.studentUsernameID);
        password = findViewById(R.id.studentPasswordId);
        firstname = findViewById(R.id.studentFirstNameId);
        lastname = findViewById(R.id.studentLastNameId);
        studentID = findViewById(R.id.studentId);
        String emptyString = "";
        username.setText(emptyString);
        password.setText(emptyString);
        firstname.setText(emptyString);
        lastname.setText(emptyString);
        studentID.setText(emptyString);
    }
}