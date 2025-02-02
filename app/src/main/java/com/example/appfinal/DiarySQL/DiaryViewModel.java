package com.example.appfinal.DiarySQL;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiaryViewModel extends AndroidViewModel {
    private DiaryDao diaryDao;
    private LiveData<List<DiaryBean>> allDiaries;
    private Executor executor = Executors.newSingleThreadExecutor();

    public DiaryViewModel(Application application) {
        super(application);
        DiaryDatabase db = DiaryDatabase.getDatabase(application);
        diaryDao = db.diaryDao();
        allDiaries = diaryDao.getAllDiaries();
    }

    public LiveData<List<DiaryBean>> getAllDiaries() {
        return allDiaries;
    }

    public void insert(DiaryBean diary) {
        executor.execute(() -> diaryDao.insert(diary));
    }

    public void update(DiaryBean diary) {
        executor.execute(() -> diaryDao.update(diary));
    }

    public void delete(DiaryBean diary) {
        executor.execute(() -> diaryDao.delete(diary));
    }

    public void deleteAll() {
        executor.execute(diaryDao::deleteAll);
    }

    public void getDiaryByTitle(String title, OnDiaryFetchedListener listener) {
        executor.execute(() -> {
            DiaryBean diary = diaryDao.getDiaryByTitle(title);
            listener.onDiaryFetched(diary);
        });
    }
    public LiveData<List<DiaryBean>> getDiariesByUname(String uname) {
        return diaryDao.getDiariesByUname(uname);
    }
    public interface OnDiaryFetchedListener {
        void onDiaryFetched(DiaryBean diary);
    }
}