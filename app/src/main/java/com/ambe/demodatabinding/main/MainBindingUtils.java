package com.ambe.demodatabinding.main;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * Created by AMBE on 10/1/2018 at 3:20 PM.
 */
public class MainBindingUtils {
    @BindingAdapter("adapter")
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("isSelected")
    public static void setCheckBoxSelected(CheckBox view, boolean isSelected) {
        view.setChecked(isSelected);
    }


    @BindingAdapter("listenner")
    public static void setCheckBoxListenner(CheckBox view, CompoundButton.OnCheckedChangeListener listener) {
        view.setOnCheckedChangeListener(listener);
    }
}
