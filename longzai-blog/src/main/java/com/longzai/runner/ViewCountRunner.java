package com.longzai.runner;

import com.longzai.domain.entity.Article;
import com.longzai.mapper.ArticleMapper;
import com.longzai.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息 id文章到id viewCount文章到浏览量
        List<Article> list = articleMapper.selectList(null);
        Map<String, Integer> collect = list.stream()
                .collect(Collectors.toMap(new Function<Article, String>() {
                    @Override
                    public String apply(Article article) {
                        return String.valueOf(article.getId());
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));
        // 存储到redis
        redisCache.setCacheMap("Article:viewCount",collect);

    }
}
