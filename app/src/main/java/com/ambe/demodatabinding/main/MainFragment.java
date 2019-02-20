package com.ambe.demodatabinding.main;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ambe.demodatabinding.MainActivity;
import com.ambe.demodatabinding.R;
import com.ambe.demodatabinding.data.MyDatabase;
import com.ambe.demodatabinding.data.TaskEntity;
import com.ambe.demodatabinding.data.TaskLocalDataSource;
import com.ambe.demodatabinding.data.TaskRepository;
import com.ambe.demodatabinding.databinding.FragmentMainBinding;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel mainViewModel;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TaskRepository taskRepository = TaskRepository.getInstance(TaskLocalDataSource.getInstance(MyDatabase.getInstance(getContext()).taskDAO()));
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setmTaskRepository(taskRepository);
        mainViewModel.setActivity((MainActivity) getActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setViewModel(mainViewModel);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel.getTasks().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntity> taskEntities) {
                mainViewModel.updateData(taskEntities);
            }
        });
        ((MainActivity) getActivity()).setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitleTextColor(Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                mainViewModel.deleteAllTask();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
