package myapplication.bjnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.bjnews.R;
import myapplication.bjnews.uitls.CacheUitls;
import myapplication.bjnews.uitls.DensityUtil;

public class GuideActivity extends AppCompatActivity {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.btn_start_main)
    Button btnStartMain;
    @Bind(R.id.ll_group_point)
    LinearLayout llGroupPoint;
    @Bind(R.id.iv_red_point)
    ImageView ivRedPoint;
    @Bind(R.id.activity_guide)
    RelativeLayout activityGuide;
    private ArrayList<ImageView> imageViews;
    private  int [] ids = {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private int leftMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
        viewpager.setAdapter(new MyAdapter());
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //取消监听
                ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 间距 = 第1个点距离左边的距离 - 第0个点距离左边的距离
                leftMargin = llGroupPoint.getChildAt(1).getLeft() - llGroupPoint.getChildAt(0).getLeft();
                Log.e("TAG", "leftMargin==" + leftMargin);

            }
        });
    }

    private void initData() {
         imageViews = new ArrayList<>();
        for(int i = 0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.guide_point_normal);
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(DensityUtil.dip2px(GuideActivity.this,10),DensityUtil.dip2px(GuideActivity.this,10));
            point.setLayoutParams(params);
            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(GuideActivity.this,10);
            }
            //添加到线性布局
            llGroupPoint.addView(point);
        }

    }

    @OnClick(R.id.btn_start_main)
    public void onViewClicked() {
        CacheUitls.putBoolean(this,"this_main",true);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class MyAdapter extends PagerAdapter {


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float left = leftMargin *(positionOffset + position) ;

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
            params.leftMargin = (int) left;
            ivRedPoint.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {

                btnStartMain.setVisibility(View.VISIBLE);
            } else {

                btnStartMain.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
