package com.lin.timeline.example;

import android.support.annotation.Nullable;

import com.lin.poweradapter.DefaultAdapterDelegate;
import com.lin.poweradapter.MultiAdapter;
import com.lin.poweradapter.PowerViewHolder;

/**
 *
 * Created by lin18 on 2017/8/23.
 */

public class TraceAdapter extends MultiAdapter<Trace, PowerViewHolder> {

    public TraceAdapter(int width, int height,
                        @Nullable Object listener, @Nullable OnTraceClickListener clickListener) {
        super(listener);
        delegatesManager.addDelegate(new MarkerDelegate());
        delegatesManager.addDelegate(new TextDelegate());
        delegatesManager.addDelegate(new TimeDelegate());
        delegatesManager.addDelegate(new ImageDelegate(width, height, clickListener));
        delegatesManager.addDelegate(new ButtonDelegate(clickListener));
        delegatesManager.addDelegate(new RatingDelegate());
        delegatesManager.setFallbackDelegate(new DefaultAdapterDelegate<Trace>());
    }

    public interface OnTraceClickListener {
        void onImageClick(int position);

        void onButtonClick(int position);
    }

}