package com.wxj.bean;

import com.wxj.util.ValidateUtil;

import java.util.List;

/**
 * 登录用户
 */
public class LoginUser2 {
    private List<String> rules;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 记住我
     */
    private boolean remember_me = false;


    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(boolean remember_me) {
        this.remember_me = remember_me;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public boolean isValid() {
        return !ValidateUtil.isNullOrEmpty(this.username) && !ValidateUtil.isNullOrEmpty(this.password);
    }
}

