package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.User;
import com.pt1002.modules.pojo.query.UserQuery;
import com.pt1002.modules.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/pageQuery")
    @ResponseBody
    public UserQuery pageQuery() {
        UserQuery query = userService.findPage();
        return query;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Response userUpdate(User user) {
        if (user != null && !StringUtils.isEmpty(user)) {
            userService.userUpdate(user);
            return new Response(ResponseCode.SUCCESS) ;
        }
        return new Response(ResponseCode.FALLURE);

    }

    @RequestMapping("/delete")
    public Response userDelete( String ids) {
        userService.userDelete(ids);
        return new Response(ResponseCode.SUCCESS) ;
    }

    @RequestMapping("/add")
    public Response userAdd(User user) {
        if (user != null && !StringUtils.isEmpty(user)) {
            userService.userAdd(user);
            return new Response(ResponseCode.SUCCESS) ;
        }
        return new Response(ResponseCode.FALLURE);
    }
}
