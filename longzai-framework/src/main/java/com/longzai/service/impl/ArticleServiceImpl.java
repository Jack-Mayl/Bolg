package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.mapper.ArticleMapper;
import com.longzai.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章 封装成 ResponseResult进行返回
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();

        list();
        return null;
    }
}
