package com.bw.movie.mvp;

import com.bw.movie.bean.Bean_Login;
import com.bw.movie.bean.Bean_Register;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public interface MyApiService {
   /* @FormUrlEncoded
    @POST("user/v1/login")//登录
    Observable<Bean_Login> getLogin(@Field("phone")String phone,@Field("pwd")String pwd );
    @FormUrlEncoded
    @POST("user/v1/registerUser")//注册
    Observable<Bean_Register> getRegister(@FieldMap HashMap<String,String> hashMap);*/
    //Retrofit + Rxjava
   @FormUrlEncoded
    @GET
    Observable<ResponseBody> get(@Url String url, @FieldMap  Map<String,String> map);
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap  Map<String,String> map);
}
