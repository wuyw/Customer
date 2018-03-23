package com.wxj.bean.base;

import java.util.Date;

//基本表  --   依据需求而定
public class Module {

    /**
     * 主键id
     */
    private String id;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块代码
     */
    private String sign;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
