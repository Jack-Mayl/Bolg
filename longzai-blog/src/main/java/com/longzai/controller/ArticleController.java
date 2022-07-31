package com.longzai.controller;

import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.service.ArticleService;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        // 查询热门文章 封装成 ResponseResult进行返回
        ResponseResult result=articleService.hotArticleList();
        return result;
    }
}
