package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.Constants.SystemConstants;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Comment;
import com.longzai.domain.vo.CommentVo;
import com.longzai.domain.vo.PageVo;
import com.longzai.enums.AppHttpCodeEnum;
import com.longzai.exception.SystemException;
import com.longzai.mapper.CommentMapper;
import com.longzai.service.CommentService;
import com.longzai.service.UserService;
import com.longzai.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-08-11 17:18:18
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String CommentType, Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<>();
        //对应articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(articleId),Comment::getArticleId,articleId);
        // 根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId,-1);
        //评论类型
        queryWrapper.eq(Comment::getType,CommentType);

        // 分页查询
        Page<Comment> page=new Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CommentVo> commentVoList= toCommentVoList(page.getRecords());
        // 查询所有梗评论对应的子评论集合 并赋值给对应的属性
        for (CommentVo commentVo:commentVoList) {
            //   查询对应的子评论
            List<CommentVo> children  =getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
            
        }
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    /**
     *  添加评论方法
     * @param comment
     * @return
     */
    @Override
    public ResponseResult addComment(Comment comment) {
        // 评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOtNULL);
        }
        // mybatisPlus已经自带填充了
        save(comment);
        return ResponseResult.okResult();
    }



    /**
     * 根据根评论的id查询所对应的子评论的集合
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(list);

        return commentVos;
    }

    private List<CommentVo>  toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //  遍历Vo集合
        for (CommentVo commentVo : commentVos) {
            // 通过creatyBy查询用户名称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            // 通过toCommentUserId查询用户对名称并赋值
            // 如果toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId() != -1){
                String nickName1 = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(nickName1);
            }

        }
        return commentVos;
    }
}


