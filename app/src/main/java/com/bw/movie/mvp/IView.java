package com.bw.movie.mvp;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public interface IView<X> {
    void success(X x,int type);
    void failed(String t);
}
