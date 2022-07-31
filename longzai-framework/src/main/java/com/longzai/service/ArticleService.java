package com.longzai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
