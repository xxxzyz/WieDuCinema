package com.bw.movie.bean;

/**
 * date:2018/12/27
 * author:薛鑫欣(吧啦吧啦)
 * function:
 */
public class Bean_Guide_Viewpager {
    Integer image;
    String tvup;
    String tvdown;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTvup() {
        return tvup;
    }

    public void setTvup(String tvup) {
        this.tvup = tvup;
    }

    public String getTvdown() {
        return tvdown;
    }

    public void setTvdown(String tvdown) {
        this.tvdown = tvdown;
    }

    public Bean_Guide_Viewpager(Integer image, String tvup, String tvdown) {
        this.image = image;
        this.tvup = tvup;
        this.tvdown = tvdown;
    }
}
