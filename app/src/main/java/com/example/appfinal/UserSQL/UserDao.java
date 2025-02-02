package com.example.appfinal.UserSQL;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserBean user);

    @Update
    void update(UserBean user);

    @Delete
    void delete(UserBean user);

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users")
    LiveData<List<UserBean>> getAllUsers();
    // 新增方法：根据用户名和密码查询用户
    @Query("SELECT * FROM users WHERE uname = :username AND password = :password LIMIT 1")
    UserBean getUserByUsernameAndPassword(String username, String password);
    @Query("SELECT * FROM users WHERE uname = :username LIMIT 1")
    UserBean getUserByUsername(String username);
}