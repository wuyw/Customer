package com.wxj.bean;

import com.wxj.bean.base.Role;
import com.wxj.bean.base.RolePermission;

import java.util.ArrayList;
import java.util.List;

public class RoleBean extends Role {

    private List<RolePermission> rolePermissions = new ArrayList<>();

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }


}
