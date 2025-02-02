package com.example.appfinal.DiarySQL;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DiaryBean.class}, version = 2, exportSchema = true)
public abstract class DiaryDatabase extends RoomDatabase {
    public abstract DiaryDao diaryDao();

    private static volatile DiaryDatabase INSTANCE;

    public static DiaryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiaryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DiaryDatabase.class, "diary_database")
                            .fallbackToDestructiveMigration() // 允许破坏性迁移
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}