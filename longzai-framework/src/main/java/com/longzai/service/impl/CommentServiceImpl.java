package com.longzai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.domain.entity.Comment;
import com.longzai.mapper.CommentMapper;
import com.longzai.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 17:18:18
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}


