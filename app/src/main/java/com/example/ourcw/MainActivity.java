package com.example.ourcw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourcw.models.User;

public class MainActivity extends AppCompatActivity {
    private EditText usernameView;
    private EditText passwordView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button navToRegistrationStudentButton = findViewById(R.id.navToRegistrationStudentButtonId);
        Button navToRegistrationTeacherButton = findViewById(R.id.navToRegistrationTeacherButtonId);
        usernameView = findViewById(R.id.loginUsernameId);
        passwordView = findViewById(R.id.loginPasswordId);
        Button loginButton = findViewById(R.id.loginButtonId);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();
                if (!isEmptyInput(username, password)) {
                    User user = User.getUserByID(username, password);
                    if (user != null) {
                        user.setLogin(true);
                        //intent to panel
                    } else {
                        createToast("mistake in pass or login");
                    }
                } else {
                    createToast("empty input");
                }
            }
        });
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

    private boolean isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

}