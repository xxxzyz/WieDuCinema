package com.bw.movie.mvp;
public interface MyCallBack<T> {
    void onSuccess(T t);
    void onFail(String error);
}
