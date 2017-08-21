package com.lin.timeline.example;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.SingleAdapter;

import butterknife.BindView;

/**
 * Created by owp on 2017/8/14.
 */

public class AnalogAdapter extends SingleAdapter<Analog, AnalogAdapter.ChildViewHolder>  {

    public AnalogAdapter(@Nullable Object listener) {
        super(listener);
    }

    @Override
    public ChildViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(parent, R.layout.analog_item);
    }

    @Override
    public void onBindVHolder(ChildViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Analog analog = getItem(position);
        final int color = ContextCompat.getColor(context,
                analog.isHead ? android.R.color.black : android.R.color.darker_gray);
        holder.title.setTextColor(color);
        holder.title.setText(analog.text);
        holder.subtitle.setTextColor(color);
        holder.subtitle.setText(analog.time);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.subtitle)
        TextView subtitle;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }

    }

}
