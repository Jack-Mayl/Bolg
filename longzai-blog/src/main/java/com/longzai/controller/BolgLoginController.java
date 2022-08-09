package com.longzai.controller;

import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.User;
import com.longzai.service.BolgLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BolgLoginController {
    @Autowired
    private BolgLoginService bolgLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
      return   bolgLoginService.login(user);

    }
}
