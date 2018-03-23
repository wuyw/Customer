package com.wxj.bean.base;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class Conversation {

    private Integer id; //会话ID

    private String send; //发送者

    private String receive; //接收者

    private String content; // 会话内容

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
