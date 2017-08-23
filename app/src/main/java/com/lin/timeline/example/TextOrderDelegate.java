package com.lin.timeline.example;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

/**
 *
 * Created by lin18 on 2017/8/23.
 */

public class TextOrderDelegate extends AbsOrderDelegate {

    @Override
    protected boolean isForViewType(@NonNull Order item, int position) {
        return item instanceof TextOrder;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.order_text);
    }

    @Override
    protected void onBindViewHolder(@NonNull Order item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        super.onBindViewHolder(item, position, holder, payloads);
    }

    static class ChildViewHolder extends AbsChildViewHolder {

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
