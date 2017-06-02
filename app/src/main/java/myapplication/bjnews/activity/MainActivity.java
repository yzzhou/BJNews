package myapplication.bjnews.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import myapplication.bjnews.R;
import myapplication.bjnews.fragment.ContentFragment;
import myapplication.bjnews.fragment.LeftFragment;

public class MainActivity extends SlidingFragmentActivity {
    private static final String MAIN_CONTENT = "main_content";
    private static final String LEFT_MENU = "left_menu";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSlidingMenu();
        initFragment();

    }

    private void initFragment() {
        //1.获取FragmentManager管理对象,注意不能用getFragmentManager，不支持1以下
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = fm.beginTransaction();//获取事务操作对象

        //3.替换Fragment

        //ft.replace(R.id.fl_left, new LeftMenuFragment());
        //把帧布局替换成正文Fragment
        //ft.replace(R.id.fl_main, new ContentFragment());
        //把帧布局替换成左侧菜单Fragment
        //LEFT_MENU,相当于ID，以后可以用fm.findFragmentByTag(LEFT_MENU);找得到他
        ft.replace(R.id.fl_left, new LeftFragment(),LEFT_MENU);
        //把帧布局替换成正文Fragment
        ft.replace(R.id.activity_main, new ContentFragment(),MAIN_CONTENT);

//   fm.findFragmentByTag(LEFT_MENU);

        //4.提交事务
        ft.commit();

    }

    private void initSlidingMenu() {
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
