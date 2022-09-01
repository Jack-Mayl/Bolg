package com.longzai.controller;

import com.longzai.annotation.SystemLog;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.User;
import com.longzai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user)  {
        return userService.register(user);
    }
}
