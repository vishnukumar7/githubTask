package com.app.githubtask.model;

import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DataItem dataItem);

    @Update
    void update(DataItem dataItem);

    @Query("select * from users")
    List<DataItem> getAll();

    @Query("select * from users where id=:id")
    List<DataItem> getId(int id);

    @Ignore
    default void insertOrUpdate(List<DataItem> dataItemList) {
        for (DataItem item : dataItemList) {
            if (getId(item.getId()).size() > 0)
                update(item);
            else
                insert(item);
        }
    }

    @Ignore
    default void insertOrUpdate(DataItem dataItem) {
        if (getId(dataItem.getId()).size() > 0)
            update(dataItem);
        else
            insert(dataItem);
    }
}