package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */

/**
 *   这是MVP的Activity的抽象类,在对应的生命周期里绑定资源,释放资源,避免内存泄漏问题
 *
 *    V泛型:代表当前的view
 *    T泛型:代表了当前的Presenter
 *
 *   提示:因为每个Activity创建的Presenter,View,都是不同的,所以我要用到JAVA里面的泛型和多态
 */
public abstract class BaseMVPFragment<V,T extends BasePresenter<V>> extends BaseFragment {
    public T presenter;
    public abstract T initPresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         presenter=initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
