package com.lin.timeline.example;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

import butterknife.BindView;

import static com.lin.timeline.example.Trace.IMG_TYPE;

/**
 *
 * Created by lin18 on 2017/8/23.
 */

public class ImageDelegate extends AdapterDelegate<Trace, PowerViewHolder> {

    private int width;
    private int height;
    private TraceAdapter.OnTraceClickListener clickListener;

    public ImageDelegate(int width, int height, TraceAdapter.OnTraceClickListener clickListener) {
        this.width = width;
        this.height = height;
        this.clickListener = clickListener;
    }

    @Override
    protected boolean isForViewType(@NonNull Trace item, int position) {
        return IMG_TYPE.equals(item.type);
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.trace_image);
    }

    @Override
    protected void onBindViewHolder(@NonNull Trace item, final int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        final Context context = holder.itemView.getContext();
        final ChildViewHolder vh = (ChildViewHolder) holder;

        Glide.with(context).load(((Picture)item.value).thumb)
                .apply(new RequestOptions()
                        .override(width, height)
                        .placeholder(AppCompatResources.getDrawable(context, R.drawable.vector_default_image))
                        .error(AppCompatResources.getDrawable(context, R.drawable.vector_default_image))
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(vh.imageView);

        vh.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onImageClick(position);
            }
        });
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
