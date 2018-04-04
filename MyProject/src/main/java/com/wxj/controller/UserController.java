package com.wxj.controller;

import com.wxj.bean.Request.RequestPage;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;
import com.wxj.bean.dto.UserDto;
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
     * @param requestPage
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/list")
    @ResponseBody
    public ResponseBean userList(@RequestBody RequestPage requestPage) {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        Integer page = requestPage.getPage();
        Integer perPage = requestPage.getPerPage();
        return userService.getUserList(page,perPage,companyId);
    }


    /**
     * 通过ID查询客服人员信息
     * @param id
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/info")
    @ResponseBody
    public ResponseBean userList(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    /**
     * 模糊查询
     * @param user
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/search")
    @ResponseBody
    public ResponseBean userSearch(@RequestBody User user,@RequestHeader(value = "page") Integer page,@RequestHeader(value = "perPage") Integer perPage) {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        user.setCompanyId(companyId);
        return userService.getUserByParams(user,page,perPage);
    }

    /**
     * 删除客服人员
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/user/delete")
    @ResponseBody
    public ResponseBean delUser(@RequestParam Integer id){
        return userService.delUser(id);
    }

}
