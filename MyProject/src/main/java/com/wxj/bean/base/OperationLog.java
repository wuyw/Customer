package com.wxj.bean.base;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class OperationLog {

    private Integer id; //操作日志ID

    private Integer operatorId; //操作者ID

    private String  action; //动作

    private Date time; //时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
