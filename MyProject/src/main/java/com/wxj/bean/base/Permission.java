package com.wxj.bean.base;

import java.util.Date;

//基本表  --   依据需求而定
public class Permission {
    // 用于菜单显示
    public final static String PERMISSION_SHOW = "show";

    public final static String PERMISSION_CREATE = "save";

    public final static String PERMISSION_READ = "view";

    public final static String PERMISSION_UPDATE = "edit";

    public final static String PERMISSION_DELETE = "delete";

    /**
     * 权限id
     */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限代码
     */
    private String sign;

    /**
     * 所属功能模块
     */
    private String moduleId;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 所属功能模块
     */
    private Module module;

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

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
