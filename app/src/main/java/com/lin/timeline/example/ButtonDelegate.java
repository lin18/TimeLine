package com.lin.timeline.example;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

import butterknife.BindView;

import static com.lin.timeline.example.Trace.BUTTON_TYPE;

/**
 *
 * Created by lin18 on 2017/8/23.
 */

public class ButtonDelegate extends AdapterDelegate<Trace, PowerViewHolder> {

    private TraceAdapter.OnTraceClickListener clickListener;

    public ButtonDelegate(TraceAdapter.OnTraceClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected boolean isForViewType(@NonNull Trace item, int position) {
        return BUTTON_TYPE.equals(item.type);
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.trace_button);
    }

    @Override
    protected void onBindViewHolder(@NonNull final Trace item, final int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        final Context context = holder.itemView.getContext();
        final ChildViewHolder vh = (ChildViewHolder) holder;

        vh.button.setText(item.name);
        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onButtonClick(position);
            }
        });
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.button)
        Button button;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
