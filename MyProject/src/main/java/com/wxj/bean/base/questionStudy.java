package com.wxj.bean.base;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class questionStudy {

    private Integer id; //问题学习ID

    private String title; //问题标题

    private Date askTime; //询问时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAskTime() {
        return askTime;
    }

    public void setAskTime(Date askTime) {
        this.askTime = askTime;
    }
}
