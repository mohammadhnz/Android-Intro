package com.example.ourcw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourcw.controllers.Database;
import com.example.ourcw.controllers.Exceptions.LoginExceptions;
import com.example.ourcw.controllers.Exceptions.LoginOnceException;
import com.example.ourcw.controllers.UserController;
import com.example.ourcw.models.User;

public class MainActivity extends AppCompatActivity {
    TextView textView1, textView22;
    Animation textViewAnimation;
    private SharedPreferences sharedPreferences;
    private static final String preferencesKey = "OurCw";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        textView1 = findViewById(R.id.textView1);
        textView22 = findViewById(R.id.textView22);
        textViewAnimation = AnimationUtils.loadAnimation(this, R.anim.animate_main_activity);
        textView1.setAnimation(textViewAnimation);
        textView22.setAnimation(textViewAnimation);
        final Runnable startFirstPage = new Runnable() {
            public void run() {
                Database.getInstance().retrieveAllData(getSharedPreferences(preferencesKey, Context.MODE_PRIVATE));
                Intent intent = new Intent(MainActivity.this, FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(startFirstPage, 3000);
   }
}