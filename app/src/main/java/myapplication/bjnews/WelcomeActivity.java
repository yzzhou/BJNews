package myapplication.bjnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import myapplication.bjnews.activity.GuideActivity;
import myapplication.bjnews.activity.MainActivity;
import myapplication.bjnews.uitls.CacheUitls;

public class WelcomeActivity extends AppCompatActivity {
    private RelativeLayout activity_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        activity_welcome = (RelativeLayout)findViewById(R.id.activity_welcome);


        //旋转动画：旋转中心，页面的中心，旋转度数：0~360
        RotateAnimation rotateAnim = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(2000);//设置动画时长
        rotateAnim.setFillAfter(true);//设置状态停留在播放后状态

        //缩放动画：大小从0~1变大,缩放中心：界面中心
        ScaleAnimation scaleAnim = new  ScaleAnimation(0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(2000);
        scaleAnim.setFillBefore(true);

        //渐变动画：透明度0~1变大
        AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
        alphaAnim.setDuration(2000);
        alphaAnim.setFillAfter(true);

        //添加动画-没有先后顺序
        AnimationSet animaSet = new AnimationSet(false);
        animaSet.addAnimation(rotateAnim);
        animaSet.addAnimation(alphaAnim);
        animaSet.addAnimation(scaleAnim);

        //设置控件播放动画
        activity_welcome.startAnimation(animaSet);
        animaSet.setAnimationListener(new MyAnimationListener());

    }


    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            boolean isstartMain = CacheUitls.getBoolean(WelcomeActivity.this,"start_main");
            Intent intent =null;
            if(isstartMain){
                intent= new Intent(WelcomeActivity.this,MainActivity.class);
            }else{
               intent= new Intent(WelcomeActivity.this,GuideActivity.class);
            }
            startActivity(intent);
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
