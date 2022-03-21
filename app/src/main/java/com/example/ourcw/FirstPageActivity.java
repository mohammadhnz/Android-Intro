package com.example.ourcw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourcw.controllers.Database;
import com.example.ourcw.controllers.Exceptions.LoginExceptions;
import com.example.ourcw.controllers.Exceptions.LoginOnceException;
import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.User;

public class FirstPageActivity extends AppCompatActivity {
    private EditText usernameView;
    private EditText passwordView;
    private SharedPreferences sharedPreferences;
    private static final String preferencesKey = "OurCw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
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
                    String loginResultMessage = "";
                    try {
                        UserController.getInstance().login(username, password);
                        loginResultMessage = getString(
                                R.string.login_successful_message
                        );
                        createIntentToPanel(
                                UserController.getInstance().getCurrentUserType()
                        );
                    } catch (LoginExceptions loginExceptions) {
                        loginResultMessage = getString(
                                R.string.login_failed_message
                        );
                    } catch (LoginOnceException loginOnceException) {
                        loginResultMessage = getString(
                                R.string.once_login_error_message
                        ) + loginOnceException.getUserType();
                    } finally {
                        createToast(loginResultMessage);
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
                        FirstPageActivity.this,
                        TeacherRegistrationActivity.class
                );
                setEmptyTextEditors();
                startActivityForResult(
                        intent, 100
                );
            }
        });
        navToRegistrationStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        FirstPageActivity.this,
                        StudentRegistrationActivity.class
                );
                setEmptyTextEditors();
                startActivityForResult(
                        intent, 100
                );
            }
        });
    }

    private boolean
    isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(FirstPageActivity.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void createIntentToPanel(String userType) {
        Intent intent = null;
        if (userType.equals("Student")) {
            intent = new Intent(
                    FirstPageActivity.this,
                    StudentPanelPageActivity.class
            );
        } else {
            intent = new Intent(
                    FirstPageActivity.this,
                    TeacherPanelPageActivity.class
            );
        }
        setEmptyTextEditors();
        startActivity(intent);
    }

    private void setEmptyTextEditors(){
        usernameView = findViewById(R.id.loginUsernameId);
        passwordView = findViewById(R.id.loginPasswordId);
        usernameView.setText("");
        passwordView.setText("");
    }

}