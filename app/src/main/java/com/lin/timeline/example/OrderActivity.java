package com.lin.timeline.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lin.timeline.TimeLineDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lin.timeline.TimeLineDecoration.BEGIN;
import static com.lin.timeline.TimeLineDecoration.CUSTOM;
import static com.lin.timeline.TimeLineDecoration.END;
import static com.lin.timeline.TimeLineDecoration.NORMAL;

/**
 * Created by lin18 on 2017/8/23.
 */
public class OrderActivity extends AppCompatActivity implements OrderAdapter.OnOrderClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final TimeLineDecoration decoration = new TimeLineDecoration(this)
                .setLineColor(android.R.color.black)
                .setLineWidth(1)
                .setLeftDistance(93)
                .setTopDistance(10)
                .setBeginMarker(R.drawable.begin_up)
                .setEndMarker(R.drawable.up)
                .setCustomMarker(R.drawable.up)
                .setMarkerRadius(4)
                .setMarkerColor(R.color.colorAccent)
                .setCallback(new TimeLineDecoration.TimeLineAdapter() {

                    @Override
                    public int getTimeLineType(int position) {
                        if (position == 0) return BEGIN;
                        else if (position == adapter.getItemCount() - 1) return END;
                        else if (adapter.getItem(position).isCustom) return CUSTOM;
                        else return NORMAL;
                    }
                });
        recyclerView.addItemDecoration(decoration);

        adapter = new OrderAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        final List<Order> analogs = new ArrayList<>();

        LocationOrder locationOrder = new LocationOrder();
        locationOrder.isHead = true;
        locationOrder.title = "待提货";
        locationOrder.subTitle = "您的快件已由XXX代收，免费保管5天。取货码查询方式：物流详情页/短信/手机淘宝-消息-物流助手";
        locationOrder.latitude = 00.00d;
        locationOrder.longitude = 00.000d;
        locationOrder.time = "2016-01-08\n10:20:19";
        analogs.add(locationOrder);

        TextOrder textOrder0 = new TextOrder();
        textOrder0.isCustom = true;
        textOrder0.title = "派送中";
        textOrder0.subTitle = "【已签收，签收是8】";
        textOrder0.time = "2016-01-07\n09:15:10";
        analogs.add(textOrder0);

        TextOrder textOrder1 = new TextOrder();
        textOrder1.title = "XX市【XX站】，【XXX】正在派件 电话：12345678910";
        textOrder1.time = "2016-01-07\n07:20:45";
        analogs.add(textOrder1);

        TextOrder textOrder2 = new TextOrder();
        textOrder2.title = "XX市【XX转运中心】，正发往【XX站】";
        textOrder2.time = "2016-01-06\n16:32:51";
        analogs.add(textOrder2);

        TextOrder textOrder3 = new TextOrder();
        textOrder3.title = "到XX市【XX转运中心】";
        textOrder3.time = "2016-01-06\n13:56:03";
        analogs.add(textOrder3);

        TextOrder textOrder4 = new TextOrder();
        textOrder4.isCustom = true;
        textOrder4.title = "已揽件";
        textOrder4.subTitle = "【XX】的收件员【XX】已收件";
        textOrder4.time = "2016-01-05\n21:23:34";
        analogs.add(textOrder4);

        TextOrder textOrder5 = new TextOrder();
        textOrder5.isCustom = true;
        textOrder5.title = "已发货";
        textOrder5.subTitle = "卖家已以发货";
        textOrder5.time = "2016-01-05\n10:20:36";
        analogs.add(textOrder5);

        InfoOrder infoOrder = new InfoOrder();
        infoOrder.title = "已下单";
        infoOrder.subTitle = "商品已经下单";
        infoOrder.time = "2016-01-05\n09:10:59";
        infoOrder.url = "www.xxx.com";
        infoOrder.phone = "12345678901";
        analogs.add(infoOrder);

        adapter.setItems(analogs);
    }

    @Override
    public void onLookClick(int position) {
        Toast.makeText(this, "查看订单", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCallClick(int position) {
        Toast.makeText(this, "联系卖家", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationClick(int position) {
        Toast.makeText(this, "查看位置", Toast.LENGTH_SHORT).show();
    }
}
