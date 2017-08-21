package com.lin.timeline.example;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 *
 * Created by owp on 2017/8/8.
 */

public class InfoOrderDelegate extends AbsOrderDelegate {

    private OrderAdapter.OnOrderClickListener clickListener;

    public InfoOrderDelegate(OrderAdapter.OnOrderClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected boolean isForViewType(@NonNull Order item, int position) {
        return item instanceof InfoOrder;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.order_info);
    }

    @Override
    protected void onBindViewHolder(@NonNull Order item, final int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        super.onBindViewHolder(item, position, holder, payloads);
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLookClick(position);
            }
        });
        vh.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCallClick(position);
            }
        });
    }

    static class ChildViewHolder extends AbsChildViewHolder {

        @BindView(R.id.look)
        Button look;
        @BindView(R.id.call)
        Button call;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
