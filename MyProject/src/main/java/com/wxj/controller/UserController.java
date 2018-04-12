package com.wxj.controller;

import com.wxj.bean.Request.RequestObject;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;
import com.wxj.service.UserService;
import com.wxj.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 新增客服人员
     * @param user
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/save")
    @ResponseBody
    public ResponseBean insertUser(@RequestBody User user) {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        user.setCompanyId(companyId);
        return userService.insertUser(user);
    }

    /**
     * 查询客服人员列表分页
     * @param requestObject
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/list")
    @ResponseBody
    public ResponseBean userList(@RequestBody RequestObject requestObject) {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        Integer page = requestObject.getPage();
        Integer perPage = requestObject.getPerPage();
        return userService.getUserList(page,perPage,companyId);
    }


    /**
     * 通过ID查询客服人员信息
     * @param user
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/info")
    @ResponseBody
    public ResponseBean userList(@RequestBody User user) {
        Integer id = user.getId();
        return userService.getUserById(id);
    }

    /**
     * 模糊查询
     * @param requestObject
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/search")
    @ResponseBody
    public ResponseBean userSearch(@RequestBody RequestObject requestObject ) {
        String keywords = requestObject.getKeywords();
        Integer page = requestObject.getPage();
        Integer perPage = requestObject.getPerPage();
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return userService.getUserByParams(keywords,companyId,page,perPage);
    }

    /**
     * 删除客服人员
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/delete")
    @ResponseBody
    public ResponseBean delUser(@RequestBody User user){
        Integer id = user.getId();
        return userService.delUser(id);
    }

    /**
     * 修改客服人员信息
     * @param user
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/update")
    @ResponseBody
    public ResponseBean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
