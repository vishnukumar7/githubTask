package com.app.githubtask.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.githubtask.model.DataItem;
import com.app.githubtask.model.UserDao;

@Database(entities = {DataItem.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
