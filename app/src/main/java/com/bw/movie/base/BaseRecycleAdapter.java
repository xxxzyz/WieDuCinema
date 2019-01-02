package com.bw.movie.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xxx.wieducinema.app.MyApplication;

/**
 * date:2018/12/27
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public abstract class BaseRecycleAdapter <T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    Activity mActivity;
    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mActivity).inflate(initView(), parent, false);
        return initHolder(inflate);
    }

    /**
     * 设置Item布局文件
     * @return
     */
    protected abstract int initView();

    /**
     * 设置Holder
     * @param itemView
     * @return
     */
    protected abstract T initHolder(View itemView);

    /**
     * 设置List的长度
     * @return
     */
    public abstract int getDataCount();
    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        setView(holder, position);
    }

    /**
     * 设置View
     * @param holder
     * @param position
     */
    protected abstract void setView(@NonNull T holder, int position);

    @Override
    public int getItemCount() {
        return getDataCount();
    }
}
