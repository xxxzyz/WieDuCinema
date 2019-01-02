package com.bw.movie.mvp;

import com.bw.movie.bean.Bean_Login;
import com.bw.movie.bean.Bean_Register;
import com.bw.movie.util.Util_Retrofit;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class MyModel {
    /*//登录
    public Observable<Bean_Login> getLoginData(String phone,String pwd) {
        MyApiService myApi=Util_Retrofit.getInstance().create(MyApiService.class);
        Observable<Bean_Login> data=myApi.getLogin(phone,pwd);
        return data;
    }
  //注册
    public Observable<Bean_Register> getRegisterData(HashMap hashMap) {
        MyApiService myApi=Util_Retrofit.getInstance().create(MyApiService.class);
        Observable<Bean_Register> data=myApi.getRegister(hashMap);
        return data;
    }*/

    public void requestData(String url, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
        //一个小小的建造者模式带给大家
        Util_Retrofit.getInstance().post(url, params).setHttpListener(new Util_Retrofit.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                callBack.onSuccess(o);
            }

            @Override
            public void onError(String error) {
                callBack.onFail(error);
            }
        });
    }

}
