package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;

    public Course() {}
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
