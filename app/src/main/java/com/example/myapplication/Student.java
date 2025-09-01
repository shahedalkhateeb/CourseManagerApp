package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(entity = Course.class,
                parentColumns = "id",
                childColumns = "courseId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("courseId")}
)
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int courseId;
    public String name;
    public String email;

    public Student() {}
    public Student(int courseId, String name, String email) {
        this.courseId = courseId;
        this.name = name;
        this.email = email;
    }
}
