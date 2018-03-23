package com.wxj.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    /**
     * 获取所有的角色名称
     *
     * @return
     */
//    @Select("SELECT name FROM keta_role")
//    List<String> findAllName();
//
//    /**
//     * 获取所有的角色列表
//     *
//     * @param key
//     * @param start
//     * @param perPage
//     * @return
//     */
//    List<Role> getRoles(@Param("key") String key, @Param("start") Integer start, @Param("perPage") Integer perPage);
//
//    /**
//     * 获取所有的角色数量
//     *
//     * @param key
//     * @return
//     */
//    Integer countRoles(@Param("key") String key);
//
//    /**
//     * 获取某个角色的具体信息
//     *
//     * @param roleId
//     * @return
//     */
//    RoleInfo getRoleInfo(@Param("role_id") Long roleId);
//
//    /**
//     * 获取角色功能模块信息
//     *
//     * @param roleId
//     * @return
//     */
//    List<ModuleInfo> getRoleModuleInfo(@Param("role_id") Long roleId);
//
//    /**
//     * 角色名是否重复
//     *
//     * @param roleId
//     * @param roleName
//     * @return
//     */
//    String isExistRoleName(@Param("role_name") String roleName, @Param("role_id") Long roleId);
//
//    /**
//     * 添加角色并且返回主键
//     *
//     * @param requestBean
//     * @return
//     */
//    Integer addRoleAndGetId(@Param("requestBean") HttpRequestAddRole requestBean);
//
//    /**
//     * 给角色添加权限
//     *
//     * @param authIds
//     * @param roleId
//     * @return
//     */
//    Integer addRoleAuth(@Param("auth_ids") Long[] authIds, @Param("role_id") Long roleId);
//
//    /**
//     * 更新角色表
//     *
//     * @param requestBean
//     * @return
//     */
//    Integer updateRole(@Param("requestBean") HttpRequestUpdateRole requestBean);
//
//    /**
//     * 删除角色权限
//     *
//     * @param delAuths
//     * @param roleId
//     * @return
//     */
//    Integer deleteRoleAuth(@Param("auth_ids") List<Long> delAuths, @Param("role_id") Long roleId);
//
//    /**
//     * 获取某个角色的所有权限id
//     *
//     * @param roleId
//     * @return
//     */
//    @Select("SELECT permission_id FROM keta_role_permission WHERE role_id=#{role_id}")
//    List<Long> getRoleAuths(@Param("role_id") Long roleId);
//
//    /**
//     * 删除角色
//     *
//     * @param roleIds
//     * @return
//     */
//    Integer deleteRole(@Param("role_ids") List<Long> roleIds);
//
//    /**
//     * 删除角色的所有权限信息
//     *
//     * @param roleIds
//     * @return
//     */
//    Integer deleteRolePermission(@Param("role_ids") List<Long> roleIds);
}
