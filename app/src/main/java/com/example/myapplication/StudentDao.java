package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    @Query("SELECT * FROM Student WHERE courseId = :courseId ORDER BY id DESC")
    LiveData<List<Student>> getStudentsByCourse(int courseId);
}
