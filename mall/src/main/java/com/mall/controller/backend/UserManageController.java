package com.mall.controller.backend;

import com.mall.common.Const;
import com.mall.common.ServerResponse;
import com.mall.entity.User;
import com.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Copyright (C), 2017-2018
 *
 * @FileName: UserManageController
 * @Author: root1
 * @Date: 18-1-11 下午4:27
 * @Description: 后台用户管理
 * History:
 */
@Controller
@RequestMapping(value = "/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST )
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
          if (response.isSuccess()){
              User user = response.getData();
              if (user.getRole()== Const.Role.ROLE_ADMIN){
                  //说明是管理员
                  session.setAttribute(Const.CURRENT_USER,user);
                  return response;
              }else {
                  return ServerResponse.createByErrorMessage("你不是管理员，不能登录");
              }
          }
          return response;
    }
}
