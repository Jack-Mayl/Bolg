package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.Constants.SystemConstants;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Article;
import com.longzai.domain.vo.ArticleListVo;
import com.longzai.domain.vo.HotArticleVo;
import com.longzai.domain.vo.PageVo;
import com.longzai.mapper.ArticleMapper;
import com.longzai.service.ArticleService;
import com.longzai.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public ResponseResult<Article> articleList(Integer pageNum, Integer pageSize, Long categoryId) {

            // 查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            // 如果有categoryId 就要 查询时要和传入的时间
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId > 0,Article::getCategoryId,categoryId);
            // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
            // 对isTop进行排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
            // 分页查询
        Page<Article> page =new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
            // 查询articleName
            // 封装查询结果VO
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo=new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);

    }
}
