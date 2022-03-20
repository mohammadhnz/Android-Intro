package com.example.ourcw.controllers;

import android.content.SharedPreferences;

import com.example.ourcw.models.Student;
import com.example.ourcw.models.Teacher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Database {
    private static Database database;
    private boolean dataRetrievedAlready = false;
    private SharedPreferences sharedPreferences = null;

    private Database() {
    }

    public static Database getInstance() {
        if (database == null) {
            synchronized (Database.class) {
                if (database == null) {
                    database = new Database();
                }
            }
        }
        return database;
    }

    public void updateData(String key, String data) {
        SharedPreferences.Editor sharedPreferencesEditor = this.sharedPreferences.edit();
        sharedPreferencesEditor.remove(key);
        sharedPreferencesEditor.commit();
        sharedPreferencesEditor.putString(key, data);
        sharedPreferencesEditor.commit();
        String z = this.sharedPreferences.getString(key, "");
    }

    public void saveData() {
        String allStudentsData = new Gson().toJson(UserController.getInstance().getAllStudents());
    }

    public void retrieveAllData(SharedPreferences sharedPreferences) {
        if (this.dataRetrievedAlready) {
            return;
        }
        this.sharedPreferences = sharedPreferences;
        String studentsData = sharedPreferences.getString("all_students", "");
        String teachersData = sharedPreferences.getString("all_teachers", "");
        Gson gson = new Gson();

        retrieveStudentsData(teachersData, gson);
        retrieveTeachersData(studentsData, gson);

        UserController.getInstance().resetAllUsers();

        this.dataRetrievedAlready = true;
    }

    private void retrieveTeachersData(String studentsData, Gson gson) {
        ArrayList<Student> allStudents = new ArrayList<>();
        Type studentsListType = new TypeToken<ArrayList<Student>>() {
        }.getType();
        allStudents = gson.fromJson(studentsData, studentsListType);
        if (allStudents != null) {
            UserController.getInstance().setAllStudents(allStudents);
        }
    }

    private void retrieveStudentsData(String teachersData, Gson gson) {
        ArrayList<Teacher> allTeachers = new ArrayList<>();
        Type teachersListType = new TypeToken<ArrayList<Teacher>>() {
        }.getType();
        allTeachers = gson.fromJson(teachersData, teachersListType);
        if (allTeachers != null) {
            UserController.getInstance().setAllTeachers(allTeachers);
        }
    }


}
