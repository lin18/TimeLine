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
 * Created by lin18 on 2017/8/23.
 */

public class LocationOrderDelegate extends AbsOrderDelegate {

    private OrderAdapter.OnOrderClickListener clickListener;

    public LocationOrderDelegate(OrderAdapter.OnOrderClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected boolean isForViewType(@NonNull Order item, int position) {
        return item instanceof LocationOrder;
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.order_location);
    }

    @Override
    protected void onBindViewHolder(@NonNull Order item, final int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        super.onBindViewHolder(item, position, holder, payloads);
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLocationClick(position);
            }
        });
    }

    static class ChildViewHolder extends AbsChildViewHolder {

        @BindView(R.id.location)
        Button location;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
