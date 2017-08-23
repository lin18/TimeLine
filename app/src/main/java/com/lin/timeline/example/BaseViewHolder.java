package com.lin.timeline.example;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lin.poweradapter.PowerViewHolder;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lin18 on 2017/8/23.
 */

public class BaseViewHolder extends PowerViewHolder {

    Unbinder unbinder;

    public BaseViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
        this(LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false));
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        unbinder = ButterKnife.bind(this, itemView);
    }
}
