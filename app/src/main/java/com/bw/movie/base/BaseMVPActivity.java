package com.bw.movie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.widget.BaseAdapter;

import com.bw.movie.MainActivity;

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
public abstract class BaseMVPActivity<V,T extends BasePresenter> extends BaseActivity {
    public T presenter;
    public abstract T initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
