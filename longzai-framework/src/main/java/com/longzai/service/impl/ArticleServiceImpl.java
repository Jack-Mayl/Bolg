package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.Constants.SystemConstants;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.domain.vo.HotArticleVo;
import com.longzai.mapper.ArticleMapper;
import com.longzai.service.ArticleService;
import com.longzai.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        // 查询热门文章 封装成 ResponseResult进行返回
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        // 必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        // 最多查询10条
        Page<Article> page = new Page<>(SystemConstants.ARTICLE_STATUS_DRAFT,SystemConstants.ARTICLE_NUMBER_PER);
        page(page,queryWrapper);
        List<Article> records = page.getRecords();

        // bean的拷贝
//        List<HotArticleVo> articlesVos = new ArrayList<>();
//        for (Article record : records) {
//            HotArticleVo hotArticleVo = new HotArticleVo();
//            BeanUtils.copyProperties(record,hotArticleVo);
//            articlesVos.add(hotArticleVo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }
}
