package myapplication.bjnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import myapplication.bjnews.base.BasePager;

/**
 * Created by zhouzhou on 2017/6/2.
 */

public class SettingPager extends BasePager {
    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("设置");

        TextView textView = new TextView(context);
        textView.setText("设置页面的内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);

        fl_content.addView(textView);
    }
}
