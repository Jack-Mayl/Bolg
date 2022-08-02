package com.longzai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author jack-myl-java
 * @since 2022-08-02 16:41:19
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}


