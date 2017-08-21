package com.lin.timeline.example;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.lin.poweradapter.AdapterDelegate;
import com.lin.poweradapter.PowerViewHolder;

import java.util.List;

import butterknife.BindView;

import static com.lin.timeline.example.Trace.RATING_TYPE;

/**
 *
 * Created by owp on 2017/8/8.
 */

public class RatingDelegate extends AdapterDelegate<Trace, PowerViewHolder> {


    public RatingDelegate() {
    }

    @Override
    protected boolean isForViewType(@NonNull Trace item, int position) {
        return RATING_TYPE.equals(item.type);
    }

    @NonNull
    @Override
    protected PowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ChildViewHolder(parent, R.layout.trace_rating);
    }

    @Override
    protected void onBindViewHolder(@NonNull final Trace item, final int position, @NonNull PowerViewHolder holder, @NonNull List<Object> payloads) {
        final ChildViewHolder vh = (ChildViewHolder) holder;
        vh.rating.setRating((Float) item.value);
        vh.rating.setIsIndicator(true);
    }

    static class ChildViewHolder extends BaseViewHolder {

        @BindView(R.id.rating)
        RatingBar rating;

        ChildViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutResId) {
            super(parent, layoutResId);
        }
    }

}
