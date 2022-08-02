package com.longzai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.entity.Category;
import com.longzai.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import com.longzai.service.CategoryService;

/**
 * 分类表(Category)表服务实现类
 *
 * @author jack-myl-java
 * @since 2022-08-02 16:41:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}


