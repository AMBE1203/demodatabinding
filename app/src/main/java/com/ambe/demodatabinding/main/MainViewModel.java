package com.ambe.demodatabinding.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ambe.demodatabinding.MainActivity;
import com.ambe.demodatabinding.R;
import com.ambe.demodatabinding.data.TaskDataSource;
import com.ambe.demodatabinding.data.TaskEntity;

import java.util.List;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * Created by AMBE on 10/2/2018 at 9:55 AM.
 */
public class MainViewModel extends AndroidViewModel {

    private TaskDataSource mTaskRepository;
    private MainActivity mainActivity;

    public void setActivity(MainActivity activity) {
        mainActivity = activity;
    }


    public final ObservableField<TaskAdapter> adpter = new ObservableField<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setmTaskRepository(TaskDataSource taskRepository) {
        mTaskRepository = taskRepository;
        adpter.set(new TaskAdapter());
        adpter.get().setMainViewModel(this);
    }

    public LiveData<List<TaskEntity>> getTasks() {
        return mTaskRepository.getAllTask();
    }

    public void updateData(List<TaskEntity> tasks) {
        adpter.get().updateData(tasks);
    }

    public void onAddTaskClick() {
        TaskEntity task = new TaskEntity();
        task.setDescription("AMBE1203 ");
        task.setName("Le ngu ha ");
        mTaskRepository.addTask(task);
    }

    public void deleteAllTask() {
        mTaskRepository.deleteAllTask();
    }

    public void onCheckedChanged(TaskEntity task, boolean isSelected) {
        task.setActive(isSelected);
        mTaskRepository.updateTask(task);
    }

    public void onItemClicked(TaskEntity task) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("task", task);
        Navigation.findNavController(mainActivity, R.id.main_host_fragment).navigate(R.id.detailFragment, bundle);
    }
}
