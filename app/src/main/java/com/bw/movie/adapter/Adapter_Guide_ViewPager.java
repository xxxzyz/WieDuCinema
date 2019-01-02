package com.bw.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.bean.Bean_Guide_Viewpager;


import java.util.List;

/**
 * date:2018/12/27
 * author:薛鑫欣(吧啦吧啦)
 * function:引导页轮播图
 */
public class Adapter_Guide_ViewPager extends PagerAdapter {
    List<Bean_Guide_Viewpager> list;
    MainActivity mMainActivity;
    public Adapter_Guide_ViewPager(MainActivity mainActivity, List<Bean_Guide_Viewpager> viewpagerlist) {
        mMainActivity=mainActivity;
        list=viewpagerlist;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //设置布局
        View view=View.inflate(mMainActivity, R.layout.guide_viewpager,null);
        //查找viewpager里面控件id
        ImageView image=view.findViewById(R.id.guide_viewpager_image);
        TextView tvup=view.findViewById(R.id.guide_viewpager_tvup);
        TextView tvdown=view.findViewById(R.id.guide_viewpager_tvdown);
        image.setImageResource(list.get(position).getImage());
        tvup.setText(list.get(position).getTvup());
        tvdown.setText(list.get(position).getTvdown());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
