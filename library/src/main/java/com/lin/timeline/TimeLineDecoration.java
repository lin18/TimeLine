package com.lin.timeline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * Created by lin18 on 2017/8/14.
 */

public class TimeLineDecoration extends RecyclerView.ItemDecoration {

    public static final int NORMAL = 0;
    public static final int BEGIN = 1;
    public static final int END = 2;
    public static final int END_FULL = 3;
    public static final int LINE = 4;
    public static final int LINE_FULL = 5;
    public static final int CUSTOM = 6;

    @IntDef({NORMAL, BEGIN, END, END_FULL, LINE, LINE_FULL, CUSTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeLineType {
    }

    public interface TimeLineCallback {

        boolean isShowDivider(int position);

        @Nullable
        Rect getRect(int position);

        @TimeLineType
        int getTimeLineType(int position);

    }

    private float dividerHeight;
    private float dividerPaddingLeft;
    private float dividerPaddingRight;
    @NonNull
    private Paint dividerPaint;

    private int lineWidth;
    @NonNull
    private Paint linePaint;

    private int leftDistance;
    private int topDistance;

    @NonNull
    private Paint markerPaint;
    private int markerRadius;

    @Nullable
    private Drawable beginMarker;
    private int beginMarkerRadius;
    @Nullable
    private Drawable endMarker;
    private int endMarkerRadius;
    @Nullable
    private Drawable normalMarker;
    private int normalMarkerRadius;
    @Nullable
    private Drawable customMarker;
    private int customMarkerRadius;

    private final Context context;
    @Nullable
    private TimeLineCallback callback;

    public TimeLineDecoration(@NonNull Context context) {
        this.context = context;

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(getColor(android.R.color.black));

        markerPaint = new Paint();
        markerPaint.setAntiAlias(true);
        markerPaint.setStyle(Paint.Style.FILL);
        markerPaint.setColor(context.getResources().getIdentifier("colorPrimary", "attr", context.getPackageName()));
    }

    public TimeLineDecoration setDividerHeight(@FloatRange(from = 0.0f) float height) {
        this.dividerHeight = dp2px(height);
        return this;
    }

    public TimeLineDecoration setDividerColor(@ColorRes int color) {
        dividerPaint.setColor(getColor(color));
        return this;
    }

    public TimeLineDecoration setDividerPaddingLeft(@FloatRange(from = 0.0f) float padding) {
        this.dividerPaddingLeft = dp2px(padding);
        return this;
    }

    public TimeLineDecoration setDividerPaddingRight(@FloatRange(from = 0.0f) float padding) {
        this.dividerPaddingRight = dp2px(padding);
        return this;
    }

    public TimeLineDecoration setLineColor(@ColorRes int color) {
        linePaint.setColor(getColor(color));
        return this;
    }

    public TimeLineDecoration setLineWidth(@FloatRange(from = 0.0f) float width) {
        this.lineWidth = dp2px(width);
        return this;
    }

    public TimeLineDecoration setLeftDistance(@IntRange(from = 0) int distance) {
        this.leftDistance = dp2px(distance);
        return this;
    }

    public TimeLineDecoration setTopDistance(@IntRange(from = 0) int distance) {
        this.topDistance = dp2px(distance);
        return this;
    }

    public TimeLineDecoration setMarkerColor(@ColorRes int color) {
        markerPaint.setColor(getColor(color));
        return this;
    }

    public TimeLineDecoration setMarkerRadius(@FloatRange(from = 0.0f) float radius) {
        this.markerRadius = dp2px(radius);
        return this;
    }

    public TimeLineDecoration setBeginMarkerRadius(@FloatRange(from = 0.0f) float radius) {
        beginMarkerRadius = dp2px(radius);
        return this;
    }

    public TimeLineDecoration setBeginMarker(@DrawableRes int resId) {
        return setBeginMarker(getDrawable(resId));
    }

    public TimeLineDecoration setBeginMarker(@NonNull Drawable drawable) {
        beginMarker = drawable;
        beginMarkerRadius = beginMarker.getIntrinsicWidth() / 2;
        return this;
    }

    public TimeLineDecoration setEndMarkerRadius(@FloatRange(from = 0.0f) float radius) {
        endMarkerRadius = dp2px(radius);
        return this;
    }

    public TimeLineDecoration setEndMarker(@DrawableRes int resId) {
        return setEndMarker(getDrawable(resId));
    }

    public TimeLineDecoration setEndMarker(@NonNull Drawable drawable) {
        endMarker = drawable;
        endMarkerRadius = endMarker.getIntrinsicWidth() / 2;
        return this;
    }

    public TimeLineDecoration setNormalMarkerRadius(@FloatRange(from = 0.0f) float radius) {
        normalMarkerRadius = dp2px(radius);
        return this;
    }

    public TimeLineDecoration setNormalMarker(@DrawableRes int resId) {
        return setNormalMarker(getDrawable(resId));
    }

    public TimeLineDecoration setNormalMarker(@NonNull Drawable drawable) {
        normalMarker = drawable;
        normalMarkerRadius = normalMarker.getIntrinsicWidth() / 2;
        return this;
    }

    public TimeLineDecoration setCustomMarker(@DrawableRes int resId) {
        return setCustomMarker(getDrawable(resId));
    }

    public TimeLineDecoration setCustomMarker(@NonNull Drawable drawable) {
        customMarker = drawable;
        customMarkerRadius = customMarker.getIntrinsicWidth() / 2;
        return this;
    }

    public TimeLineDecoration setCallback(@Nullable TimeLineCallback callback) {
        this.callback = callback;
        return this;
    }

    private int dp2px(@FloatRange(from = 0.0f) float dpValue) {
        return dp2px(context, dpValue);
    }

    public static int dp2px(Context context, @FloatRange(from = 0.0f) float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    @ColorInt
    private int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (callback == null) return;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(view);
            if (callback.isShowDivider(position))
                drawDivider(canvas, view, parent.getRight());
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (callback == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            final Rect rect = callback.getRect(parent.getChildAdapterPosition(view));
            outRect.set(rect == null ? new Rect() : rect);
        }
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (callback == null) return;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View view = parent.getChildAt(i);
            final int position = parent.getChildAdapterPosition(view);

            final Rect rect = callback.getRect(position);
            final int bottomMargin = ((ViewGroup.MarginLayoutParams)view.getLayoutParams()).bottomMargin + (rect == null ? 0 : rect.bottom);
            final int topMargin = ((ViewGroup.MarginLayoutParams)view.getLayoutParams()).topMargin + (rect == null ? 0 : rect.top);

            switch (callback.getTimeLineType(position)) {
                case NORMAL:
                    drawLine(canvas, view.getTop() - topMargin, view.getBottom() + bottomMargin);
                    drawMarkerOrCircle(canvas, view, normalMarker, normalMarkerRadius);
                    break;

                case BEGIN:
                    drawLine(canvas, view.getTop() + topDistance + beginMarkerRadius, view.getBottom() + bottomMargin);
                    drawMarkerOrCircle(canvas, view, beginMarker, beginMarkerRadius);
                    break;

                case END:
                    drawLine(canvas, view.getTop() - topMargin, view.getTop() + bottomMargin + topDistance + (endMarker == null ? markerRadius : endMarkerRadius));
                    drawMarkerOrCircle(canvas, view, endMarker, endMarkerRadius);
                    break;

                case END_FULL:
                    drawLine(canvas, view.getTop() - topMargin, parent.getBottom());
                    drawMarkerOrCircle(canvas, view, endMarker, endMarkerRadius);
                    break;

                case LINE:
                    drawLine(canvas, view.getTop() - topMargin, view.getBottom() + bottomMargin);
                    break;

                case LINE_FULL:
                    drawLine(canvas, view.getTop() - topMargin, parent.getBottom());
                    break;

                case CUSTOM:
                    drawLine(canvas, view.getTop() - topMargin, view.getBottom() + bottomMargin);
                    drawMarkerOrCircle(canvas, view, customMarker, customMarkerRadius);
                    break;
            }
        }
    }

    private void drawDivider(Canvas canvas, View view, int right) {
        canvas.drawRect(dividerPaddingLeft, view.getBottom() - dividerHeight,
                right - dividerPaddingRight, view.getBottom(), dividerPaint);
    }

    private void drawLine(Canvas canvas, float top, float bottom) {
        canvas.drawRect(leftDistance, top, leftDistance + lineWidth, bottom, linePaint);
    }

    private void drawMarkerOrCircle(Canvas canvas, View view, Drawable marker, int radius) {
        if (marker != null)
            drawMarker(canvas, view, marker, radius);
        else
            drawCircle(canvas, view);
    }

    private void drawCircle(Canvas canvas, View view) {
        canvas.drawCircle(leftDistance + lineWidth / 2, view.getTop() + topDistance + markerRadius * 2,
                markerRadius, markerPaint);
    }

    private void drawMarker(Canvas canvas, View view, Drawable marker, int radius) {
        marker.setBounds(leftDistance + lineWidth / 2 - radius, view.getTop() + topDistance,
                leftDistance + lineWidth / 2 + radius, view.getTop() + topDistance + radius * 2);
        marker.draw(canvas);
    }

}
