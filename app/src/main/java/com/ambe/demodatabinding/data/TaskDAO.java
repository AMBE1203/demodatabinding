package com.ambe.demodatabinding.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by AMBE on 10/1/2018 at 1:34 PM.
 */
@Dao
public interface TaskDAO {
    @Query("select * from tasks")
    LiveData<List<TaskEntity>> getAllTask();

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    LiveData<TaskEntity> getTaskById(int taskId);

    @Insert(onConflict = REPLACE)
    void addTask(TaskEntity task);

    @Query("DELETE FROM tasks")
    void deleteAllTask();

    @Update(onConflict = REPLACE)
    void updateTask(TaskEntity... tasks);
}
