package com.ambe.demodatabinding.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ambe.demodatabinding.MainActivity;
import com.ambe.demodatabinding.R;
import com.ambe.demodatabinding.data.TaskEntity;
import com.ambe.demodatabinding.data.TaskRepository;

import androidx.navigation.Navigation;

/**
 * Created by AMBE on 10/2/2018 at 1:48 PM.
 */
public class DetailViewModel extends AndroidViewModel {

    private ObservableField<TaskEntity> mTask = new ObservableField<>();
    private TaskRepository taskRepository;
    private MainActivity mainActivity;

    public void setActivity(MainActivity activity) {
        mainActivity = activity;
    }

    public void setTask(TaskEntity task) {
        mTask.set(task);
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ObservableField<TaskEntity> getTask() {
        return mTask;
    }

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void onSaveTaskClicked() {

        taskRepository.updateTask(mTask.get());
        Navigation.findNavController(mainActivity, R.id.main_host_fragment).navigateUp();


    }
}
