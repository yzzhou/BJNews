package myapplication.bjnews.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import myapplication.bjnews.base.BaseFragment;

/**
 * Created by zhouzhou on 2017/6/2.
 */

public class LeftFragment extends BaseFragment {

    @Override
    public View initVeiw() {
        TextView textView = new TextView(context);
        textView.setText("我是左侧菜单的布局");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        return textView;
    }
}
