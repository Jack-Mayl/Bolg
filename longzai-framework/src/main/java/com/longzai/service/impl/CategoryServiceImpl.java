package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.Constants.SystemConstants;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.domain.entity.Category;
import com.longzai.mapper.CategoryMapper;
import com.longzai.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longzai.service.CategoryService;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author jack-myl-java
 * @since 2022-08-02 16:41:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService service;
    @Override
    public ResponseResult getCategoryList() {
        // 查询文章表状态已发布的
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = service.list(articleWrapper);
        // 获取文章的id 并且去重
        // 查询分类表
        // 封装VC
        return null;
    }
}


