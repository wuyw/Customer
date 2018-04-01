package com.wxj.bean.base;

import com.wxj.util.ValidateUtil;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class User {

    private Integer id; //客服人员ID

    private Integer companyId; //公司ID

    private String account; //登录账号

    private String name; //姓名

    private String nickname; //昵称

    private String mobile; //手机号

    private String email; //邮箱

    private String password; //密码

    private Integer role; //角色（1超级管理员，2普通客服，3工单客服）

    private Integer maxReception; //最大接待量

    private int isDel;//是否删除 0否 1是

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getMaxReception() {
        return maxReception;
    }

    public void setMaxReception(Integer maxReception) {
        this.maxReception = maxReception;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public boolean isValid() {
        return !ValidateUtil.isEmpty(account) && !ValidateUtil.isEmpty(name) && !ValidateUtil.isEmpty(nickname) && !ValidateUtil.isEmpty(password) && !ValidateUtil.isEmpty(role) && !ValidateUtil.isEmpty(maxReception);
    }
}
