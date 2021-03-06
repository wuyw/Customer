package com.wxj.bean.base;

import com.wxj.util.ValidateUtil;

import java.util.Date;

/**
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class Company {

    private Integer id; //企业账号ID

    private String companyName; //企业公司名称

    private String account; //登录账号邮箱

    private String password; //账号密码

    private String companyDomain; //企业域名

    private String linkman; //企业联系人

    private String mobile; //手机号

    private String email; //邮箱

    private Date createTime; //创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyDomain() {
        return companyDomain;
    }

    public void setCompanyDomain(String companyDomain) {
        this.companyDomain = companyDomain;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isValid() {
        return !ValidateUtil.isNullOrEmpty(this.companyName) && !ValidateUtil.isNullOrEmpty(this.account) && !ValidateUtil.isNullOrEmpty(this.password) && !ValidateUtil.isNullOrEmpty(this.companyDomain);
    }
}
