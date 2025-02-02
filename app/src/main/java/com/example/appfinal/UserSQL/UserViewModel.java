package com.example.appfinal.UserSQL;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;
    private LiveData<List<UserBean>> allUsers;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UserViewModel(Application application) {
        super(application);
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<UserBean>> getAllUsers() {
        return allUsers;
    }

    public void insert(UserBean user) {
        executor.execute(() -> userDao.insert(user));
    }

    public void update(UserBean user) {
        executor.execute(() -> userDao.update(user));
    }

    public void delete(UserBean user) {
        executor.execute(() -> userDao.delete(user));
    }

    public void deleteAll() {
        executor.execute(userDao::deleteAll);
    }

    public void getUserByUsernameAndPassword(String username, String password, OnUserFetchedListener listener) {
        executor.execute(() -> {
            UserBean user = userDao.getUserByUsernameAndPassword(username, password);
            listener.onUserFetched(user);
        });
    }
    public void getUserByUsername(String username, OnUserFetchedListener listener) {
        executor.execute(() -> {
            UserBean user = userDao.getUserByUsername(username);
            listener.onUserFetched(user);
        });
    }

    public interface OnUserFetchedListener {
        void onUserFetched(UserBean user);
    }
}