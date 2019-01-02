package com.bw.movie.base;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public abstract class BasePresenter<T>  {
    public T view;
    public void attach(T view) {
      this.view=view;
    }

    public void detach() {
        this.view=null;
    }
}
