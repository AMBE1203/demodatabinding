package com.ambe.demodatabinding.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambe.demodatabinding.R;
import com.ambe.demodatabinding.data.TaskEntity;
import com.ambe.demodatabinding.databinding.ItemTaskBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMBE on 10/1/2018 at 3:35 PM.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<TaskEntity> mTask;
    private MainViewModel mainViewModel;

    public TaskAdapter() {
        mTask = new ArrayList<>();
    }

    public void setMainViewModel(MainViewModel viewModel) {
        mainViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTaskBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_task, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binData(mTask.get(i));

    }

    @Override
    public int getItemCount() {
        return mTask != null ? mTask.size() : 0;
    }

    public void updateData(List<TaskEntity> taskEntities) {
        if (taskEntities == null) {
            return;
        }
        mTask.clear();
        mTask.addAll(taskEntities);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTaskBinding mBinding;

        public ViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;


        }

        public void binData(TaskEntity taskEntity) {
            mBinding.setTask(taskEntity);
            mBinding.setViewModel(mainViewModel);
            mBinding.executePendingBindings();
        }

    }
}
