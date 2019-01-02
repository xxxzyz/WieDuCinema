package com.bw.movie.view.activitys;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.Bean_Register;
import com.bw.movie.mvp.IView;
import com.bw.movie.mvp.MyPresenter;
import com.bw.movie.util.EncryptUtil;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashMap;

/**
 * date:2018/12/28
 * author:薛鑫欣(吧啦吧啦)
 * function:注册相关类
 */
public class RegisterActivity extends BaseMVPActivity<IView,MyPresenter> implements IView, View.OnClickListener {

    private EditText register_ed_nickname;
    private EditText register_ed_sex;
    private EditText register_ed_date;
    private EditText register_ed_phone;
    private EditText register_ed_email;
    private EditText register_ed_pwd;
    private Button register_btn_register;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        register_ed_nickname = (EditText) findViewById(R.id.register_ed_nickname);
        register_ed_sex = (EditText) findViewById(R.id.register_ed_sex);
        register_ed_date = (EditText) findViewById(R.id.register_ed_date);
        register_ed_phone = (EditText) findViewById(R.id.register_ed_phone);
        register_ed_email = (EditText) findViewById(R.id.register_ed_email);
        register_ed_pwd = (EditText) findViewById(R.id.register_ed_pwd);
        register_btn_register=findViewById(R.id.register_btn_register);
        register_btn_register.setOnClickListener(this);
        register_ed_date.setOnClickListener(this);

    }




    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }


    @Override
    public MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    public void success(Object o,int type) {
        switch (type){
            case 1:
                Bean_Register bean_register= (Bean_Register) o;
                if(bean_register.getStatus().equals("0000")){//注册成功
                    showToast(bean_register.getMessage());
                    finish();
                } else {//注册失败
                    showToast(bean_register.getMessage());
                }
                break;

        }

    }

    @Override
    public void failed(String t) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击按钮注册
            case R.id.register_btn_register:
                //获得注册页面输入框的值
                String nickname = register_ed_nickname.getText().toString().trim();
                String sex = register_ed_sex.getText().toString().trim();
                String email = register_ed_email.getText().toString().trim();
                String phone = register_ed_phone.getText().toString().trim();
                String pwd = register_ed_pwd.getText().toString().trim();
                String date = register_ed_date.getText().toString().trim();
                //加密后的密码
                String encryptPwd = EncryptUtil.encrypt(pwd);
                //非空校验
                isTrue(nickname,sex,phone,pwd,date,email);
                //存入用户注册需要的信息
                HashMap hashMap = new HashMap();
                hashMap.put("nickName", nickname);
                hashMap.put("phone", phone);
                hashMap.put("pwd", encryptPwd);
                hashMap.put("pwd2", encryptPwd);
                hashMap.put("sex", sex);
                hashMap.put("birthday", date);
                hashMap.put("imei", "354853093959843");
                hashMap.put("ua", "苹果X");
                hashMap.put("screenSize", "5.0");
                hashMap.put("os", "iphone");
                hashMap.put("email", email);
                //进行注册
                presenter.startRequest("user/v1/registerUser",hashMap,Bean_Register.class);
                break;
            case R.id.register_ed_date:
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(5);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {

                        register_ed_date.setText(date.getYear()+"-"+date.getMonth()+"-"+date.getDate());
                    }
                });
                dialog.show();

                break;
        }
    }
 /*
 判断是否输入呢呢绒为空
  */
    private void isTrue(String nickname, String sex, String phone, String pwd, String date, String email) {
        if(TextUtils.isEmpty(nickname)){
            showToast("昵称不能为空");
            return;
        }
        if(TextUtils.isEmpty(sex)){
            showToast("性别不能为空");
            return;
        } else {
            if(sex.equals("男")){
                sex="1";
            } else if(sex.equals("女")){
                sex="2";
            } else{
                showToast("性别只能为：男 女");
                return;
            }
        }
        if(TextUtils.isEmpty(date)){
            showToast("出生日期不能为空哦");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            showToast("手机号不能为空");
            return;
        }
        if(TextUtils.isEmpty(email)){
            showToast("邮箱不能为空");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            showToast("登录密码不能为空");
            return;
        }

    }
}
