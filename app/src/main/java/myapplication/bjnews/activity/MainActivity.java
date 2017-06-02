package myapplication.bjnews.activity;

import android.os.Bundle;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import myapplication.bjnews.R;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.activity_left);

        //3.配置菜单使用模式：左侧可用
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置左边可以使用
        slidingMenu.setMode(SlidingMenu.LEFT);

        //4.设置拖拽区域：整个屏幕
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //5.配置主页面留着屏幕的宽度：200像素
        slidingMenu.setBehindOffset(200);

    }
}
