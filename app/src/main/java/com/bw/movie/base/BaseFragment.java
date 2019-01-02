package com.bw.movie.base;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bw.movie.view.activitys.NoNetWorkActivity;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

  View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(initLayout(), container, false);
        //初始化控件
        init(mView);
        return mView;
    }
    //初始化布局
    protected abstract int initLayout();
   //初始化控件
    private void init(View view) {
            initView(view);
            initData();

    }



    //判断有网
    private boolean isHaveNetWork(BaseFragment baseFragment) {
        ConnectivityManager manager= (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null){
            return true;
        }
        return false;
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    /**
     * 为子类提供一个权限检查方法
     * String... permission表示不定参数，也就是调用这个方法的时候这里可以传入多个String对象(JDK5新特性)
     * @return
     */
    //String... permission表示不定参数，也就是调用这个方法的时候这里可以传入多个String对象(JDK5新特性)
    public boolean hasPermission(String... permissions ){
        for(String permission: permissions ){
            if (ContextCompat.checkSelfPermission(getActivity(),permission) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            //打电话权限的回调处理
            case 0:
                //判断打电话申请权限是否成功,成功就执行打电话的逻辑
                //注意:因为集合里只有一个权限申请,所以参数为0代表打电话权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    //用户拒绝了权限请求,给用户提示权限的功能
                    Toast.makeText(getActivity(), "权限没有授予", Toast.LENGTH_SHORT).show();
                }
                break;
            //SD卡权限的回调处理
            case 1:
                //判断打电话申请权限是否成功,成功就执行打电话的逻辑
                //注意:因为集合里只有一个权限申请,所以参数为0代表打电话权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doSdCardPermission();
                } else {
                    //用户拒绝了权限请求,给用户提示权限的功能
                    Toast.makeText(getActivity(), "权限没有授予", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    /**
     * 暴露给子类实现打电话业务逻辑的方法,子类如果有此功能,覆写此方法即可,不用再管权限的配置
     * 子类没有此功能,就不用管此方法
     */
    public void doCallPhone() {
    }

    /**
     * 暴露给子类实现往SD写入数据业务逻辑的方法,子类如果有此功能,覆写此方法即可,不用再管权限的配置
     * 子类没有此功能,就不用管此方法
     */
    public void doSdCardPermission() {}
    //吐司
    public  Toast mToast;
    /**
     * 弹出吐司
     *  Toast的基类抽取
     * @param text  要传入的消息
     */
    public void showToast(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }

    }
    /**
     *  跳转activity
     * @param clazz 目标的activity
     * @param isFinish 是否销毁
     */
    public void startActivity(Class clazz,boolean isFinish) {
        startActivity(new Intent(getActivity(),clazz));
        if (isFinish) {
            getActivity().finish();
        }
    }
}
