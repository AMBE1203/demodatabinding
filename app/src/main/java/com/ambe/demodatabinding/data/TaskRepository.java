package com.ambe.demodatabinding.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

/**
 * Created by AMBE on 10/1/2018 at 1:46 PM.
 */
public class TaskRepository implements TaskDataSource {
    private static TaskRepository mInstance;
    private TaskDataSource mTasDataSource;

    public static TaskRepository getInstance(TaskDataSource taskLocalDataSource) {
        if (mInstance == null) {
            mInstance = new TaskRepository(taskLocalDataSource);
        }
        return mInstance;
    }

    public TaskRepository(TaskDataSource mTasDataSource) {
        this.mTasDataSource = mTasDataSource;
    }

    @Override
    public LiveData<TaskEntity> getTaskById(int taskId) {
        return mTasDataSource.getTaskById(taskId);
    }

    @Override
    public LiveData<List<TaskEntity>> getAllTask() {
        return mTasDataSource.getAllTask();
    }

    @Override
    public void addTask(TaskEntity task) {
        mTasDataSource.addTask(task);

    }

    @Override
    public void updateTask(TaskEntity task) {

        Log.d("AMBE1203", " update");
        mTasDataSource.updateTask(task);

    }

    @Override
    public void deleteAllTask() {
        mTasDataSource.deleteAllTask();

    }
}
