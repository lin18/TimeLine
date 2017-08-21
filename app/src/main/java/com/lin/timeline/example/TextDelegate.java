package com.lin.timeline.example;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

import butterknife.BindView;

import static com.lin.timeline.example.Trace.TEXT_TYPE;


/**
 *
 * Created by owp on 2017/8/8.
 */

public class TextDelegate extends AdapterDelegate<Trace, PowerViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull Trace item, int position) {
        return TEXT_TYPE.equals(item.type);
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.trace_text);
    }

    @Override
    protected void onBindViewHolder(@NonNull Trace item, int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        final Context context = holder.itemView.getContext();
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.textView.setTextColor(ContextCompat.getColor(context, item.isHead ? R.color.item_title_color : R.color.item_color));
        vh.textView.setText(TextUtils.isEmpty(item.name) ? item.value.toString() : (item.name + "：" + item.value.toString()));
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(android.R.id.title)
        TextView textView;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
