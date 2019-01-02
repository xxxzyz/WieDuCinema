package com.bw.movie.view.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

/**
 * date:2018/12/28
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class StartActivity extends BaseActivity {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };


    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
         //在启动页等待3秒后进行跳转到登录页面
          new Handler().postDelayed(new Runnable() {
              @Override
              public void run() {
                //跳转到登录页面
                      startActivity(LoginActivity.class,true);

              }
          },3000);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }
}
