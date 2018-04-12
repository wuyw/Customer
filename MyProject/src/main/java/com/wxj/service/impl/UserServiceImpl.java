package com.wxj.service.impl;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.Company;
import com.wxj.bean.base.User;
import com.wxj.bean.dto.UserDto;
import com.wxj.mapper.UserMapper;
import com.wxj.service.UserService;
import com.wxj.service.CompanyService;
import com.wxj.shiro.StatelessAuthenticationToken;
import com.wxj.util.JsonWebTokenUtil;
import com.wxj.util.LogUtil;
import com.wxj.util.SecurityUtils;
import com.wxj.util.ValidateUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(CompanyService.class);

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    LogUtil logUtil;

    @Autowired
    UserMapper userMapper;

    public ResponseBean insertUser(User user){

        ResponseBean responseBean = new ResponseBean();
        //判断信息是否为空
        if (!user.isValid()){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        //账号重复性验证
        User userValidate = userMapper.getUserByCompanyIdAndAccount(user.getCompanyId(),user.getAccount());
        if(!ValidateUtil.isEmpty(userValidate)){
            responseBean.setCode(ResponseBean.CODE_Value_EXIST);
            return responseBean;
        }
        // 插入数据
        int i= userMapper.insertUser(user);
        if (i > 0){
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } else {
            responseBean.setCode(ResponseBean.CODE_FAIL);
        }
        return responseBean;
    }

    /**
     * 登录
     * @param loginUser
     * @return
     */
    @Override
    public ResponseBean toLogin(LoginUser loginUser) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数是否为空
        if (!loginUser.isValid()) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        //记住密码
        if (ValidateUtil.isNullOrEmpty(loginUser.getRemember_me())) {
            loginUser.setRemember_me(false);
        }
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            user.logout();
        }
        Integer expire = 120;
        if (loginUser.getRemember_me()) {
            expire = 43200;
        }
        String jwt = new JsonWebTokenUtil(jwtKey).getJWTString(loginUser.getAccount(),loginUser.getCompanyDomain(), expire);
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(loginUser.getAccount(),loginUser.getCompanyDomain(), loginUser.getPassword(), jwt);
        try {
            user.login(token);
            result.put("token", jwt);
            responseBean.setResult(result);
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } catch (Exception e) {
            logger.error("[login] err: " + e);
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            logger.info("[login] user is not validate");
        }
        return responseBean;
    }

    /**
     * 根据公司ID和账号查询用户
     * @param companyId,account
     * @return
     */
    public User getUserByCompanyIdAndAccount(Integer companyId,String account){
     User user = userMapper.getUserByCompanyIdAndAccount(companyId,account);
        return user;
    }

    /**
     * 查询客服人员分页
     * @param page
     * @param perPage
     * @param companyId
     * @return
     */
    public ResponseBean getUserList(Integer page,Integer perPage,Integer companyId){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数是否为空
        if (companyId == null || page == null || perPage == null) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        int start = (page-1)*perPage;
        int end = perPage*page;
        List<User> userList = userMapper.getUserList(companyId,start,end);
        if (userList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            return responseBean;
        }
        int total = getUserCount(companyId);
        result.put("total",total);
        result.put("userList",userList);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 获取公司客服人员数量
     * @param companyId
     * @return
     */
    public int getUserCount(Integer companyId){
        return userMapper.getUserCount(companyId);
    }

    /**
     * 通过ID查询客服信息
     * @param id
     * @return
     */
    public ResponseBean getUserById(Integer id){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        if (id == null){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        User user = userMapper.getUserById(id);
        if (ValidateUtil.isEmpty(user)){
            responseBean.setCode(ResponseBean.CODE_NO_RESULT);
            return responseBean;
        }
        result.put("user",user);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 模糊查询
     * @param keywords
     * @param companyId
     * @param page
     * @param perPage
     *
     * @return
     */
    public ResponseBean getUserByParams(String keywords,Integer companyId,Integer page,Integer perPage){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        Integer start = (page-1)*perPage;
        Integer end = perPage*page;
        List<User> userList = userMapper.getUserListByParams(keywords,companyId,start,end);
        if (userList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NO_RESULT);
            return responseBean;
        }
        int total = userMapper.getUserCountByParams(keywords,companyId);
        result.put("userList",userList);
        result.put("total",total);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 删除客服人员
     * @param id
     * @return
     */
    public ResponseBean delUser(Integer id){
        ResponseBean responseBean = new ResponseBean();
        if (id == null) {
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            return responseBean;
        }
        //删除操作
        int i = userMapper.delUser(id);
        if (i<=0){
            responseBean.setCode(ResponseBean.CODE_FAIL);
        } else {
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        }
        return responseBean;
    }

    /**
     * 修改客服人员信息
     * @param user
     * @return
     */
    public ResponseBean updateUser(User user){
        ResponseBean responseBean = new ResponseBean();
        //判断信息是否为空
        if (!user.isValid()){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        int i= userMapper.updateUser(user);
        if (i > 0){
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } else {
            responseBean.setCode(ResponseBean.CODE_FAIL);
        }
        return responseBean;
    }
}
