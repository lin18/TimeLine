package com.lin.timeline.example;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lin.timeline.TimeLineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lin.timeline.TimeLineDecoration.BEGIN;
import static com.lin.timeline.TimeLineDecoration.END;
import static com.lin.timeline.TimeLineDecoration.LINE;
import static com.lin.timeline.TimeLineDecoration.NORMAL;
import static com.lin.timeline.example.Trace.BUTTON_TYPE;
import static com.lin.timeline.example.Trace.IMG_TYPE;
import static com.lin.timeline.example.Trace.MARKER_TYPE;
import static com.lin.timeline.example.Trace.RATING_TYPE;
import static com.lin.timeline.example.Trace.TEXT_TYPE;
import static com.lin.timeline.example.Trace.TIME_TYPE;

/**
 * Created by lin18 on 2017/8/23.
 */
public class TraceActivity extends AppCompatActivity implements TraceAdapter.OnTraceClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    TraceAdapter adapter;

    int itemWidth;
    int itemHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        itemWidth = (getResources().getDisplayMetrics().widthPixels
                - getResources().getDimensionPixelSize(R.dimen.recycler_trace_padding_left)
                - getResources().getDimensionPixelSize(R.dimen.recycler_trace_padding_right)
                - getResources().getDimensionPixelSize(R.dimen.trace_image_padding_right) * 4) / 2;
        itemHeight = getResources().getDimensionPixelSize(R.dimen.trace_image_height);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                final Trace trace = adapter.getItem(position);
                if (trace.value instanceof Picture)
                    return 1;
                return 2;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final TimeLineDecoration decoration = new TimeLineDecoration(this)
                .setDividerHeight(0.5f)
                .setDividerColor(R.color.divider)
                .setDividerPaddingLeft(53)
                .setLineColor(R.color.divider)
                .setLineWidth(1)
                .setLeftDistance(25)
                .setTopDistance(15)
                .setNormalMarker(R.drawable.marker)
                .setBeginMarker(R.drawable.begin_marker)
                .setEndMarker(R.drawable.marker)
                .setCallback(new TimeLineDecoration.TimeLineCallback() {
                    @Override
                    public boolean isShowDivider(int position) {
                        return position != adapter.getItemCount() - 1 && TIME_TYPE.equals(adapter.getItem(position).type);
                    }

                    @Override
                    public Rect getRect(int position) {
                        return isShowDivider(position) ? new Rect(0, 0, 0, TimeLineDecoration.dp2px(TraceActivity.this, 0.5f))
                                : IMG_TYPE.equals(adapter.getItem(position).type) ? new Rect(4, 4, 4, 4) : null;
                    }

                    @Override
                    public int getTimeLineType(int position) {
                        if (position == 0) return BEGIN;
                        final Trace trace = adapter.getItem(position);
                        return MARKER_TYPE.equals(trace.type) ? (position == adapter.getItemCount() - 1 ? END : NORMAL) : LINE;
                    }
                });
        recyclerView.addItemDecoration(decoration);

        adapter = new TraceAdapter(itemWidth, itemHeight, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        final List<Trace> traces = new ArrayList<>();

        Trace<String> trace0 = new Trace<>();
        trace0.isHead = true;
        trace0.type = MARKER_TYPE;
        trace0.value = "夺杯无可奈何花落去夺";
        traces.add(trace0);

        Trace<String> trace1 = new Trace<>();
        trace1.isHead = true;
        trace1.type = TEXT_TYPE;
        trace1.value = "压下肝的广泛的豆腐干个";
        traces.add(trace1);

        Trace<String> trace2 = new Trace<>();
        trace2.isHead = true;
        trace2.type = TEXT_TYPE;
        trace2.name = "士大夫似的士大夫阿三发";
        trace2.value = "法国东方士士大夫";
        traces.add(trace2);

        Trace<String> trace3 = new Trace<>();
        trace3.isHead = true;
        trace3.type = BUTTON_TYPE;
        trace3.name = "按钮";
        traces.add(trace3);

        Trace<String> trace4 = new Trace<>();
        trace4.isHead = true;
        trace4.type = TIME_TYPE;
        trace4.value = "2017-06-23 11:22:22";
        traces.add(trace4);

        Trace<String> trace5 = new Trace<>();
        trace5.type = MARKER_TYPE;
        trace5.value = "和计划工具钢";
        traces.add(trace5);

        Trace<Picture> pictureTrace0 = new Trace<>();
        pictureTrace0.type = IMG_TYPE;
        pictureTrace0.value = new Picture("https://unsplash.it/160?image=5", "https://unsplash.it/750?image=5");
        traces.add(pictureTrace0);

        Trace<Picture> pictureTrace1 = new Trace<>();
        pictureTrace1.type = IMG_TYPE;
        pictureTrace1.value = new Picture("https://unsplash.it/160?image=360", "https://unsplash.it/750?image=360");
        traces.add(pictureTrace1);

        Trace<Picture> pictureTrace2 = new Trace<>();
        pictureTrace2.type = IMG_TYPE;
        pictureTrace2.value = new Picture("https://unsplash.it/160?image=356", "https://unsplash.it/750?image=356");
        traces.add(pictureTrace2);

        Trace<Picture> pictureTrace3 = new Trace<>();
        pictureTrace3.type = IMG_TYPE;
        pictureTrace3.value = new Picture("https://unsplash.it/160?image=351", "https://unsplash.it/750?image=351");
        traces.add(pictureTrace3);

        Trace<String> trace6 = new Trace<>();
        trace6.type = TIME_TYPE;
        trace6.value = "2017-06-23 11:43:54";
        traces.add(trace6);

        Trace<String> trace7 = new Trace<>();
        trace7.type = MARKER_TYPE;
        trace7.value = "的非官方的接口和夫似的";
        traces.add(trace7);

        Trace<String> trace8 = new Trace<>();
        trace8.type = TEXT_TYPE;
        trace8.name = "萨芬健康，发的";
        trace8.value = "将货款麻烦";
        traces.add(trace8);

        Trace<String> trace9 = new Trace<>();
        trace9.type = TIME_TYPE;
        trace9.value = "2017-06-23 11:42:37";
        traces.add(trace9);

        Trace<String> trace10 = new Trace<>();
        trace10.type = MARKER_TYPE;
        trace10.value = "犯嘀咕水电费";
        traces.add(trace10);

        Trace<Picture> pictureTrace4 = new Trace<>();
        pictureTrace4.type = IMG_TYPE;
        pictureTrace4.value = new Picture("https://unsplash.it/160?image=361", "https://unsplash.it/750?image=361");
        traces.add(pictureTrace4);

        Trace<String> trace11 = new Trace<>();
        trace11.type = TIME_TYPE;
        trace11.value = "2017-06-23 11:41:51";
        traces.add(trace11);

        Trace<String> trace12 = new Trace<>();
        trace12.type = MARKER_TYPE;
        trace12.value = "朋友提供梵";
        traces.add(trace12);

        Trace<Float> ratingTrace = new Trace<>();
        ratingTrace.type = RATING_TYPE;
        ratingTrace.value = 4.5f;
        traces.add(ratingTrace);

        Trace<String> trace13 = new Trace<>();
        trace13.type = TIME_TYPE;
        trace13.value = "2017-06-23 11:40:35";
        traces.add(trace13);

        Trace<String> trace14 = new Trace<>();
        trace14.type = MARKER_TYPE;
        trace14.value = "问的非官方";
        traces.add(trace14);

        Trace<String> trace15 = new Trace<>();
        trace15.type = TEXT_TYPE;
        trace15.value = "厂校挂钩";
        traces.add(trace15);

        Trace<String> trace16 = new Trace<>();
        trace16.type = TIME_TYPE;
        trace16.value = "2017-06-22 09:12:54";
        traces.add(trace16);

//        String data = new Gson().toJson(traces);

        adapter.setItems(traces);
    }

    @Override
    public void onImageClick(int position) {

    }

    @Override
    public void onButtonClick(int position) {

    }

}
