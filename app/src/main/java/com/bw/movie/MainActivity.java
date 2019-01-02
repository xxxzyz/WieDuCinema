package com.bw.movie;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.bw.movie.adapter.Adapter_Guide_ViewPager;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.Bean_Guide_Viewpager;
import com.bw.movie.util.ShareBean;
import com.bw.movie.view.activitys.StartActivity;



import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {


    private ViewPager guide_viewpager;
    private LinearLayout guide_dots;

    private Adapter_Guide_ViewPager mAdapter_guide_ViewPager;
    private List<Bean_Guide_Viewpager> mViewpagerlist;
    private ShareBean mShareBean;


    @Override
    protected void initData() {
      //初始化viewpager布局数据
        Bean_Guide_Viewpager guideBean1=new Bean_Guide_Viewpager(R.mipmap.yindao1,"一场电影","净化你的灵魂");
        Bean_Guide_Viewpager guideBean2=new Bean_Guide_Viewpager(R.mipmap.yindao2,"一场电影","看遍人生百态");
        Bean_Guide_Viewpager guideBean3=new Bean_Guide_Viewpager(R.mipmap.yindao3,"一场电影","荡涤你的心灵");
        Bean_Guide_Viewpager guideBean4=new Bean_Guide_Viewpager(R.mipmap.yindao4,"八维移动通信学院作品","带您开启一段美好的电影之旅");
        mViewpagerlist = new ArrayList<>();
        mViewpagerlist.add(guideBean1);
        mViewpagerlist.add(guideBean2);
        mViewpagerlist.add(guideBean3);
        mViewpagerlist.add(guideBean4);
        //添加适配器
        mAdapter_guide_ViewPager=new Adapter_Guide_ViewPager(MainActivity.this,mViewpagerlist);
        guide_viewpager.setAdapter(mAdapter_guide_ViewPager);
        //同步小圆点
        for (int i = 0; i <mViewpagerlist.size() ; i++) {
            addDots(i);//添加小圆点
        }



        //滑动页面监听
        guide_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {
                //通过得到的这个item,给text和点进行选中的设置.
                changeDot(i);
                if(i==3){
                    mShareBean.put("first",true);
                    //跳转到启动页面
                   startActivity(StartActivity.class,true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
  //改变dot
    private void changeDot(int i) {
        for(int j=0;j<mViewpagerlist.size();j++){
            //.getChildAt(x);拿到容器的子控件.得到VIew对象
            View dot=guide_dots.getChildAt(j);
            //为View设置背景图片,,使用三元运算符.如果是当前页面则圆点变红
            dot.setBackgroundResource(j==i%mViewpagerlist.size()?R.mipmap.smallcircle_black:R.mipmap.smallcircle_white);
        }

    }

    /*
    动态添加小圆点
     */
    private void addDots(int i) {
        View view=new View(MainActivity.this);
        //刚开始进入引导页，小圆点默认为黑色
        if(i==0){
            view.setBackgroundResource(R.mipmap.smallcircle_black);
        } else {
            view.setBackgroundResource(R.mipmap.smallcircle_white);
        }
      //设置圆点的宽高
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(30,30);
        //设置圆点的间距
        layoutParams.leftMargin=10;
        view.setLayoutParams(layoutParams);
        guide_dots.addView(view);
    }

    @Override
    protected void initView() {
        guide_viewpager = (ViewPager) findViewById(R.id.guide_viewpager);
         guide_dots=findViewById(R.id.guide_dots);
        mShareBean = new ShareBean(MainActivity.this,"guide");
         boolean first= (boolean) mShareBean.getSharedPreference("first",false);
         if(first){
             startActivity(new Intent(MainActivity.this,StartActivity.class));
             finish();
         }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


}
