package com.wxj.bean.base;

//基本表  --   依据需求而定
public class Role {

    /**
     * 角色id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
