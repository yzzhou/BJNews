package myapplication.bjnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhouzhou on 2017/6/2.
 */

public abstract class BaseFragment extends Fragment {
    public Context context;
    /**
     * 当Fragement创建时执行
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     *
     * Fragement创建作为一个View被显示
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initVeiw();
        return view;
    }
    /**
     * 初始化Fragment的布局文件
     * @return
     */
    public abstract  View initVeiw() ;

    /**
     * 当Activity被创建了，执行该方法，也就是说Activity已经初始化完毕。
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Fragment的数据了
        initData();
    }

    /**
     * 初始化数据，子类覆盖此方法，来实现自己的数据初始化操作
     */
    public void initData() {

    }



}
