package com.wxj.shiro;

import com.alibaba.fastjson.JSONObject;
import com.wxj.bean.*;
import com.wxj.bean.base.*;
import com.wxj.bean.shiro.ShiroUser;
import com.wxj.service.UserRoleService;
import com.wxj.service.CompanyService;
import com.wxj.service.UserService;
import com.wxj.util.JsonWebTokenUtil;
import com.wxj.util.RedisUtil;
import com.wxj.util.ValidateUtil;
import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class StatelessAuthorizingRealm extends AuthorizingRealm {
    private Logger logger = Logger.getLogger(StatelessAuthorizingRealm.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RedisUtil redisUtil;

    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * 给ShiroDbRealm提供编码信息，用于密码密码比对
     * 描述
     */
    public StatelessAuthorizingRealm() {
        super();
    }

    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        StatelessAuthenticationToken statelessToken = (StatelessAuthenticationToken) token;

        //JWT  作为为登录凭证
        String jwt = statelessToken.getToken();

        //根据登录凭证获取密钥（和客户端的一样）
        Claims claims = new JsonWebTokenUtil(jwtKey).parseJWT(jwt);

        //验证凭证 = 验证statelessToken 身份信息
        if (claims == null || claims.getExpiration().before(new Date())) {
            throw new AuthorizationException("login overtime");
        }
        //获取授权信息
        if (!ValidateUtil.isNullOrEmpty(redisUtil.getAuthInfo(jwt))) {
            //获取到授权信息，认为已登录成功
            return new SimpleAuthenticationInfo(redisUtil.getAuthInfo(jwt), jwt, getName());
        }

        //未获取到授权信息，认为还未登录成功，执行登录验证
        String password = statelessToken.getPassword();
        String domain = statelessToken.getDomain();
        Company company = companyService.getCompanyByDomain(String.valueOf(statelessToken.getDomain()));
        Integer companyId = company.getId();
        User user = userService.getUserByCompanyIdAndAccount(companyId,company.getAccount());
        if (!ValidateUtil.isNullOrEmpty(user)) {
            if (!user.getPassword().equals(password)) {
                //密码验证失败
                throw new DisabledAccountException();
            }
            ShiroUser shiroUser = new ShiroUser(String.valueOf(user.getId()), user.getAccount(), jwt);
            shiroUser.setUser(user);
            redisUtil.setAuthInfo(shiroUser, jwt);
            // 这里可以缓存认证
            return new SimpleAuthenticationInfo(shiroUser, jwt, getName());
        } else {
            throw new AuthorizationException("login fail");
        }
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        Collection<?> collection = principals.fromRealm(getName());
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ShiroUser shiroUser = (ShiroUser) collection.iterator().next();
        // 设置、更新User用户权限
        SimpleAuthorizationInfo info = redisUtil.getAuthPermission(shiroUser.getJwt());
        if (info == null || JSONObject.toJSONString(info).equals("{}")) {
            info = newAuthorizationInfo(shiroUser);
            if (!JSONObject.toJSONString(info).equals("{}")) {
                redisUtil.setAuthPermission(info, shiroUser.getJwt());
            }
        }
        return info;
    }

    private SimpleAuthorizationInfo newAuthorizationInfo(ShiroUser shiroUser) {

        // 是否启用超级管理员 && id==1为超级管理员，构造所有权限
//        if (activeRoot && shiroUser.getId() == 1) {
//            hasRoles = new HashSet<>();
//            Long time1=System.currentTimeMillis();
//            hasRoles.addAll(roleService.findAllName());
//            System.out.println("获取所有的角色所用时间"+(System.currentTimeMillis()-time1)+"毫秒");
//            hasPermissions = new HashSet<>();
//            hasPermissions.add("*");
//
//            logger.info("使用了" + shiroUser.getLoginName() + "的超级管理员权限:" + "。At " + new Date());
//            logger.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);
//            logger.info(shiroUser.getLoginName() + "拥有的权限:" + hasPermissions);

//        } else {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Collection<RoleBean> roles = new HashSet<>();
            roles.addAll(userRoleService.getRolesByUserId(shiroUser.getId()));
            info.setRoles(makeRoles(roles, shiroUser));
            info.setStringPermissions(makePermissions(roles, shiroUser));
            return info;
//       }

    }

    /**
     * 组装角色权限
     *
     * @param roles
     * @param shiroUser
     * @return
     */
    private Set<String> makeRoles(Collection<RoleBean> roles, ShiroUser shiroUser) {
        Set<String> hasRoles = new HashSet<>();
        for (Role role : roles) {
            hasRoles.add(role.getRoleName());
        }
        logger.info(shiroUser.getLoginName() + "拥有的角色:" + hasRoles);

        return hasRoles;
    }

    /**
     * 组装资源操作权限
     *
     * @param roles
     * @param shiroUser
     * @return
     */
    private Set<String> makePermissions(Collection<RoleBean> roles, ShiroUser shiroUser) {
        // 清空shiroUser中map
        //shiroUser.getHasDataControls().clear();
        shiroUser.getHasModules().clear();

        Set<String> stringPermissions = new HashSet<>();
        for (RoleBean role : roles) {
            if (role.getId().equals(1L)||role.getId().equals(11L)){
                stringPermissions.add("*");
            } else {
                List<RolePermission> rolePermissions = role.getRolePermissions();
                for (RolePermission rolePermission : rolePermissions) {
                    Permission permission = rolePermission.getPermission();
                    String resource = permission.getModule().getSign();
                    String operate = permission.getSign();

                    StringBuilder builder = new StringBuilder();
                    builder.append(resource + ":" + operate);

                    shiroUser.getHasModules().put(resource, permission.getModule());

                    StringBuilder dcBuilder = new StringBuilder();

//                for (RolePermissionDataControl rpdc : rolePermission.getRolePermissionDataControls()) {
//                    DataControl dataControl = rpdc.getDataControl();
//                    dcBuilder.append(dataControl.getName() + ",");
//
//                    shiroUser.getHasDataControls().put(dataControl.getName(), dataControl);
//                }
//
//                if (dcBuilder.length() > 0) {
//                    builder.append(":" + dcBuilder.substring(0, dcBuilder.length() - 1));
//                }
                    stringPermissions.add(builder.toString());
            }
            }
        }

        logger.info(shiroUser.getLoginName() + "拥有的权限:" + stringPermissions);

        return stringPermissions;
    }

    public static class HashPassword {
        public String salt;
        public String password;
    }


}