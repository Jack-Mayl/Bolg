package com.longzai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longzai.Constants.SystemConstants;
import com.longzai.domain.ResponseResult;
import com.longzai.domain.entity.Link;
import com.longzai.domain.vo.LinkVo;
import com.longzai.mapper.LinkMapper;
import com.longzai.service.LinkService;
import com.longzai.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-08-08 15:15:35
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

//    @Autowired


    @Override
    public ResponseResult getAllLink() {
        // 查询所有审核通过的
        LambdaQueryWrapper<Link> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(lambdaQueryWrapper);
        // 转化成VO
        List<LinkVo> vs = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        // 封装返回
        return ResponseResult.okResult(vs);
    }
}


