package com.longzai.utils;

import com.longzai.domain.entity.LoginUser;

import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtils
{

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
            LoginUser loginUser = (LoginUser) getAuthentication().getPrincipal();

    return loginUser;

    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        Authentication authentication;

            authentication = SecurityContextHolder.getContext().getAuthentication();


        return authentication;
    }

    /**
     * 管理员用户
     * @return
     */
    public static Boolean isAdmin(){
       String id = getLoginUser().getUser().getId();
        return id != null &&  id.equals("1");
    }

    public static String getUserId() {
        return getLoginUser().getUser().getId();
    }
}