package myapplication.bjnews.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.bjnews.R;
import myapplication.bjnews.activity.MainActivity;
import myapplication.bjnews.base.BaseFragment;
import myapplication.bjnews.base.BasePager;
import myapplication.bjnews.pager.HomePager;
import myapplication.bjnews.pager.NewsPager;
import myapplication.bjnews.pager.NoScrollViewPager;
import myapplication.bjnews.pager.SettingPager;

/**
 * Created by zhouzhou on 2017/6/2.
 */

public class ContentFragment extends BaseFragment {

    @Bind(R.id.vp)
    NoScrollViewPager vp;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BasePager> pagers;

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
        pagers = new ArrayList<>();
        pagers.add(new HomePager(context));
        pagers.add(new NewsPager(context));
        pagers.add(new SettingPager(context));
        vp.setAdapter(new MyAdapter());
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        vp.setCurrentItem(0,false);
                        break;
                    case R.id.rb_news:
                        vp.setCurrentItem(1,false);
                        break;
                    case R.id.rb_setting:
                        vp.setCurrentItem(2,false);
                        break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pagers.get(position).initData();
                if(position ==1){
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else{
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagers.get(0).initData();
        rgMain.check(R.id.rb_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = pagers.get(position);
            View rootView = basePager.rootView;
            basePager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }
    }
}
