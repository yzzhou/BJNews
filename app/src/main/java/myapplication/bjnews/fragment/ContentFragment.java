package myapplication.bjnews.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.bjnews.R;
import myapplication.bjnews.base.BaseFragment;

/**
 * Created by zhouzhou on 2017/6/2.
 */

public class ContentFragment extends BaseFragment {

    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    public View initVeiw() {
//        TextView textView = new TextView(context);
//        textView.setText("我是正文的布局");
//        textView.setTextSize(20);
//        textView.setTextColor(Color.BLACK);
        View view = View.inflate(context, R.layout.fragment_content, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        rgMain.check(R.id.rb_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
