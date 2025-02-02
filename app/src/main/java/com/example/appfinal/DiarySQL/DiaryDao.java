package com.example.appfinal.DiarySQL;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DiaryDao {
    @Insert
    void insert(DiaryBean diary);

    @Update
    void update(DiaryBean diary);

    @Delete
    void delete(DiaryBean diary);

    @Query("DELETE FROM diaries")
    void deleteAll();

    @Query("SELECT * FROM diaries ORDER BY date DESC")
    LiveData<List<DiaryBean>> getAllDiaries();

    // 根据标题查询日记
    @Query("SELECT * FROM diaries WHERE title = :title LIMIT 1")
    DiaryBean getDiaryByTitle(String title);
    @Query("SELECT * FROM diaries WHERE uname = :uname ORDER BY date DESC")
    LiveData<List<DiaryBean>> getDiariesByUname(String uname);
}