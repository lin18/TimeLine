# TimeLine
Android Timeline View is used to display views like Tracking of shipment/order, steppers etc.

<br>![](https://github.com/lin18/TimeLine/blob/master/screenshots/20170821160031.png?raw=true)
![](https://github.com/lin18/TimeLine/blob/master/screenshots/20170821160048.png?raw=true)
![](https://github.com/lin18/TimeLine/blob/master/screenshots/20170821160103.png?raw=true)

# Use
```gradle
compile 'com.lin:timeline:1.0@aar'
```

``` Java
        final TimeLineDecoration decoration = new TimeLineDecoration(this)
                .setLineColor(android.R.color.black)
                .setLineWidth(1)
                .setLeftDistance(16)
                .setTopDistance(16)
                .setBeginMarker(R.drawable.begin_marker)
                .setMarkerRadius(4)
                .setMarkerColor(R.color.colorAccent)
                .setCallback(new TimeLineDecoration.TimeLineCallback() {
                    @Override
                    public boolean isShowDivider(int position) {
                        return false;
                    }

                    @Nullable
                    @Override
                    public Rect getRect(int position) {
                        return new Rect(0, 16, 0, 16);
                    }

                    @Override
                    public int getTimeLineType(int position) {
                        if (position == 0) return BEGIN;
                        else if (position == adapter.getItemCount() - 1) return END_FULL;
                        else return NORMAL;
                    }
                });
        recyclerView.addItemDecoration(decoration);
```
<br>如果要把时间轴写到item中，建议使用
<br>[Timeline-View](https://github.com/vipulasri/Timeline-View)
<br>瀑布流可以使用
<br>[TimeLine](https://github.com/vivian8725118/TimeLine)

<br>ps:demo中adpter使用的库来自[PowerAdapter](https://github.com/lin18/PowerAdapter)

# License
```
Copyright 2016 lin18

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

