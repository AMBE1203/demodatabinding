package com.ambe.demodatabinding.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by AMBE on 10/1/2018 at 1:42 PM.
 */
public class TaskLocalDataSource implements TaskDataSource {

    public static TaskLocalDataSource mInstance;
    private TaskDAO taskDAO;

    public static TaskLocalDataSource getInstance(TaskDAO taskDAO) {
        if (mInstance == null) {
            mInstance = new TaskLocalDataSource(taskDAO);
        }
        return mInstance;
    }

    public TaskLocalDataSource(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public LiveData<TaskEntity> getTaskById(int taskId) {
        return taskDAO.getTaskById(taskId);
    }

    @Override
    public LiveData<List<TaskEntity>> getAllTask() {
        return taskDAO.getAllTask();
    }

    @Override
    public void addTask(TaskEntity task) {
        taskDAO.addTask(task);

    }

    @Override
    public void updateTask(TaskEntity task) {
        taskDAO.updateTask(task);

    }

    @Override
    public void deleteAllTask() {
        taskDAO.deleteAllTask();

    }
}
