package com.ambe.demodatabinding.detail;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ambe.demodatabinding.MainActivity;
import com.ambe.demodatabinding.R;
import com.ambe.demodatabinding.data.MyDatabase;
import com.ambe.demodatabinding.data.TaskEntity;
import com.ambe.demodatabinding.data.TaskLocalDataSource;
import com.ambe.demodatabinding.data.TaskRepository;
import com.ambe.demodatabinding.databinding.FragmentDetailBinding;
import com.ambe.demodatabinding.main.MainViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private TaskRepository taskRepository;
    private TaskEntity taskEntity;
    private DetailViewModel viewModel;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        taskEntity = bundle.getParcelable("task");

        taskRepository = TaskRepository.getInstance(
                TaskLocalDataSource.getInstance(MyDatabase.getInstance(getActivity()).taskDAO()));
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.setTaskRepository(taskRepository);
        viewModel.setTask(taskEntity);
        viewModel.setActivity((MainActivity) getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        ((MainActivity) getActivity()).setSupportActionBar(binding.toolbar2);
        binding.toolbar2.setTitleTextColor(Color.WHITE);
        binding.toolbar2.setDrawingCacheBackgroundColor(Color.WHITE);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar2.setTitle(taskEntity.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
        }
        return true;
    }
}
