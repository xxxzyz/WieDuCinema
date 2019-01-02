package com.bw.movie.util;

import com.bw.movie.mvp.MyApiService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * date:2018/12/27
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class Util_Retrofit {
    /*    private static Retrofit retrofit;
        private static final String BASE_URL = "http://172.17.8.100/movieApi/";*/
      /*  public static final class SINGLE_INSTANCE{
            public static final Util_Retrofit _INSTANCE = new Util_Retrofit();
        }
        public static Util_Retrofit getInstance(){
            return SINGLE_INSTANCE._INSTANCE;
        }
       private Util_Retrofit(){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(buildOkhttpClient())
                    .build();
        }
        private OkHttpClient buildOkhttpClient() {
            return new OkHttpClient.Builder()
                    .writeTimeout(3000, TimeUnit.MILLISECONDS)
                    .readTimeout(3000,TimeUnit.MILLISECONDS)
                    .build();
        }
        public Retrofit getRetrofit(){
            return retrofit;
        }
        public static <T> T create(Class<T> clazz){
            return retrofit.create(clazz);
        }
*/
    private MyApiService myApiService;

    private Util_Retrofit() {
        //HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                //.addInterceptor(loggingInterceptor)
                //配置此客户端，以便在遇到连接问题时重试或不重试。默认情况下，
                //*该客户端从以下问题中悄悄恢复
                .retryOnConnectionFailure(true)
                .build();
        //初始化Retrofit 并结合各种操作
        Retrofit retrofit = new Retrofit.Builder()
                //结合Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                //结合Rxjava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://172.17.8.100/movieApi/")
                .client(okHttpClient)
                .build();
        //通过Retrofit创建完 这个ApiService 就可以调用方法了
        myApiService = retrofit.create(MyApiService.class);
    }

    public static Util_Retrofit getInstance() {
        return RetroHolder.retro;
    }

    private static class RetroHolder {
        private static final Util_Retrofit retro = new Util_Retrofit();
    }
    //封装Get方式  这里面采用构造者模式  就是调用这个方法有返回自己本身这个对象
    public Util_Retrofit get(String url, Map<String, String> map) {
        //这个订阅事件（处理网络请求）放生那个线程
        //Schedulers 线程调度器
        myApiService.get(url, map).subscribeOn(Schedulers.io())//io就是子线程
                //在主线程调用
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return Util_Retrofit.getInstance();
    }


    /**
     * 表单post请求
     */
    public Util_Retrofit post(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }

        myApiService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return Util_Retrofit.getInstance();
    }
    //子类使用
    private Subscriber<ResponseBody> subscriber = new Subscriber<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {

        }
    };
    //重写一个观察者对象
    private Observer observer = new Observer<ResponseBody>() {

        @Override
        public void onCompleted() {

        }
        //网络处理失败
        @Override
        public void onError(Throwable e) {
            if (httpListener != null) {
                httpListener.onError(e.getMessage());
            }
        }
        //网络处理成功
        @Override
        public void onNext(ResponseBody responseBody) {
            if (httpListener != null) {
                try {
                    httpListener.onSuccess(responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public interface HttpListener {
        void onSuccess(String jsonStr);

        void onError(String error);
    }

    private HttpListener httpListener;

    public void setHttpListener(HttpListener listener) {
        this.httpListener = listener;
    }





}
