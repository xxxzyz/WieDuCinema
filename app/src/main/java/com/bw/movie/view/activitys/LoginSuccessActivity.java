package com.bw.movie.view.activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.view.fragment.CinemaFragment;
import com.bw.movie.view.fragment.FilmFragment;
import com.bw.movie.view.fragment.MyFragment;

/**
 * date:2018/12/29
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class LoginSuccessActivity extends BaseActivity {
    private RadioGroup loginsuccess_change_alpha;
    FragmentManager mFragmentManager;
    private RadioButton loginsuccess_rb_film,loginsuccess_rb_cinema,loginsuccess_rb_my;

    @Override
    protected void initData() {
   mFragmentManager=getFragmentManager();
   //登录成功之后默认为影片页面
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.replace(R.id.loginsuccess_fragment,new FilmFragment());
        transaction.commit();
        loginsuccess_change_alpha.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.loginsuccess_rb_film:
                        FragmentTransaction transaction1=mFragmentManager.beginTransaction();
                        transaction1.replace(R.id.loginsuccess_fragment,new FilmFragment());
                        transaction1.commit();
                        break;
                    case R.id.loginsuccess_rb_cinema:
                        FragmentTransaction transaction2=mFragmentManager.beginTransaction();
                        transaction2.replace(R.id.loginsuccess_fragment,new CinemaFragment());
                        transaction2.commit();
                        break;
                    case R.id.loginsuccess_rb_my:

                        FragmentTransaction transaction3=mFragmentManager.beginTransaction();
                        transaction3.replace(R.id.loginsuccess_fragment,new MyFragment());
                        transaction3.commit();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        loginsuccess_change_alpha=findViewById(R.id.loginsuccess_change_alpha);
        loginsuccess_change_alpha.getBackground().setAlpha(100);
        loginsuccess_rb_film=findViewById(R.id.loginsuccess_rb_film);
        loginsuccess_rb_cinema=findViewById(R.id.loginsuccess_rb_cinema);
        loginsuccess_rb_my=findViewById(R.id.loginsuccess_rb_my);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_success;
    }
}
