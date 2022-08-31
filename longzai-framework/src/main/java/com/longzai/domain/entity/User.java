package com.longzai.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2022-08-08 16:53:27
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User   implements Serializable{
    //主键@TableId
    private String id;


    @NotNull
    //用户名
    private String userName;
    //昵称
    @NotNull
    private String nickName;
    //密码
    @Size(min=6,max=12,message="密码长度为6-12位英文,数字,下划线")
    @NotNull
    private String password;
    //用户类型：0代表普通用户，1代表管理员
    private String type;
    //账号状态（0正常 1停用）
    private String status;
    //邮箱
    @NotNull
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    private String sex;
    //头像
    private String avatar;
    //创建人的用户id
    private String createBy;
    //创建时间
    private Date createTime;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}


