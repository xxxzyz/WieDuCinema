package com.bw.movie.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.Bean_Login;
import com.bw.movie.mvp.IView;
import com.bw.movie.mvp.MyPresenter;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.ShareBean;

import java.util.HashMap;

/**
 * date:2018/12/28
 * author:薛鑫欣(吧啦吧啦)
 * function:登录相关类
 */
public class LoginActivity extends BaseMVPActivity<IView,MyPresenter> implements View.OnClickListener, IView {
  EditText login_ed_phone;
  EditText login_ed_pwd;
  TextView login_tv_toregister;
  Button login_btn_login;
  private CheckBox login_ck_jzmm,login_ck_zddl;
    private String mPhone;
    private String mPwd;
    private ShareBean shareBean;

    @Override
    protected void initData() {
        shareBean = new ShareBean(LoginActivity.this,"login");
        String sb_phone= (String) shareBean.getSharedPreference("phone","");
        String sb_pwd= (String) shareBean.getSharedPreference("pwd","");
        boolean sb_jzmm= (boolean) shareBean.getSharedPreference("isJzmm",false);
        boolean sb_zddl= (boolean) shareBean.getSharedPreference("isZddl",false);
        //设置文本内容

        if(sb_jzmm){//判断sp里面是否记住密码
            //记住密码的状态
            login_ed_phone.setText(sb_phone);
            login_ed_pwd.setText(sb_pwd);
            login_ck_jzmm.setChecked(true);
        } else {
            //没有记住密码状态
            login_ed_phone.setText(sb_phone);
            login_ed_pwd.setText(sb_pwd);
            login_ck_jzmm.setChecked(false);
        }
        if(sb_zddl){//判断sp里面是否自动
            //跳转到登录成功的页面
            startActivity(LoginSuccessActivity.class,true);
        }

    }

    @Override
    protected void initView() {
        login_ed_phone=findViewById(R.id.login_ed_phone);
        login_ed_pwd=findViewById(R.id.login_ed_password);
        login_tv_toregister=findViewById(R.id.login_tv_toregister);
        login_btn_login=findViewById(R.id.login_btn_login);
        login_tv_toregister.setOnClickListener(this);
        login_btn_login.setOnClickListener(this);
        login_ck_jzmm=findViewById(R.id.login_ck_jzmm);
        login_ck_zddl=findViewById(R.id.login_ck_zddl);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }



    @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.login_tv_toregister://跳转到注册页面
       startActivity(RegisterActivity.class,false);
        break;
        case R.id.login_btn_login://点击登录按钮进行登录
            //获得输入框得手机号密码的值
            mPhone = login_ed_phone.getText().toString().trim();
            mPwd = login_ed_pwd.getText().toString().trim();
            //非空校验
            if(TextUtils.isEmpty(mPhone)){
                showToast("手机号不能为空");
                return;
            }
            if(TextUtils.isEmpty(mPwd)){
                showToast("密码不能为空");
                return;
            }

            //加密转换
            String encryptPwd=EncryptUtil.encrypt(mPwd);
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("phone",mPhone);
            hashMap.put("pwd",encryptPwd);
            //请求数据
            presenter.startRequest("user/v1/login",hashMap,Bean_Login.class);
            break;
    }
  }


    @Override
    public MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    public void success(Object x,int type) {
        switch (type){
            case 1:
                Bean_Login bean_login= (Bean_Login) x;
                if(bean_login.getStatus().equals("0000")){
                    //登录成功
                    showToast("登录成功！！！吼吼吼~~~~");
                    /*
                    记住密码
                     */
                    if(login_ck_jzmm.isChecked()){//如果上一次点击记住密码
                        shareBean.put("phone",mPhone);
                        shareBean.put("pwd",mPwd);
                        shareBean.put("isJzmm",true);
                    } else {//如果上一次没有记住密码
                        shareBean.clear();
                    }
                    //自动登录
                    if(login_ck_zddl.isChecked()){
                        shareBean.put("isZddl",true);
                    }
                    //跳转到登录成功页面
                    startActivity(LoginSuccessActivity.class,true);
                } else{
                    //登录失败
                    showToast(bean_login.getMessage());
                }

                break;
        }

    }

    @Override
    public void failed(String t) {

    }
}
