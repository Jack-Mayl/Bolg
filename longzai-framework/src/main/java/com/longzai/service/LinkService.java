package com.longzai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-08-08 15:15:34
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}


