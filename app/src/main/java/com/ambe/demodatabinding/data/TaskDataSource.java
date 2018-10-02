package com.ambe.demodatabinding.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by AMBE on 10/1/2018 at 1:42 PM.
 */
public interface TaskDataSource {
    LiveData<TaskEntity> getTaskById(int taskId);

    LiveData<List<TaskEntity>> getAllTask();

    void addTask(TaskEntity task);

    void updateTask(TaskEntity task);

    void deleteAllTask();
}
