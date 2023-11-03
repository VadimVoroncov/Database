package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Data {
    String name;
    String surame;
    int age;

    public Data(String name, String surame, int age) {
        this.name = name;
        this.surame = surame;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurame() {
        return surame;
    }

    public void setSurame(String surame) {
        this.surame = surame;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
