package com.wxj.bean.base;


//基本表  --   依据需求而定
public class RolePermission {
    private String id;

    private String permissionId;

    private String roleId;

    private Role role;

    private Permission permission;

    //private List<RolePermissionDataControl> rolePermissionDataControls;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

//    public List<RolePermissionDataControl> getRolePermissionDataControls() {
//        return rolePermissionDataControls;
//    }
//
//    public void setRolePermissionDataControls(
//            List<RolePermissionDataControl> rolePermissionDataControls) {
//        this.rolePermissionDataControls = rolePermissionDataControls;
//    }


}
