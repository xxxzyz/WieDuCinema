package com.bw.movie.mvp;

import android.view.View;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.Bean_Login;
import com.bw.movie.bean.Bean_Register;

import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class MyPresenter extends BasePresenter<IView> {
    private final MyModel mMyModel;

    public MyPresenter() {
        mMyModel = new MyModel();
    }

   /* *//**
     * Presenter和Model层的交互,暴露一个获取数据的方法
     *//*
    public void getLoginData(String phone,String pwd) {
        mMyModel.getLoginData(phone,pwd)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Bean_Login>() {
            @Override
            public void call(Bean_Login bean_login) {
              if(bean_login!=null){
                  if(view!=null){
                      view.success(bean_login);
                  }
              }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                   view.failed(new Throwable("请求登录数据失败"));
            }
        });
    }
  //注册
    public void getRegisterData(HashMap hashMap) {
        mMyModel.getRegisterData(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bean_Register>() {
                    @Override
                    public void call(Bean_Register bean_register) {
                        if(bean_register!=null){
                            if(view!=null){
                                view.success(bean_register);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.failed(new Throwable("请求注册数据失败"));
                    }
                });

    }*/
      public void startRequest(String url, Map<String, String> params, Class clazz) {
        mMyModel.requestData(url, params, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object o) {
                view.success(o,1);
            }

            @Override
            public void onFail(String error) {
                view.failed(error);
            }
        });
    }

}
