package com.example.musicserver.service.impl;

import com.example.musicserver.entity.Comment;
import com.example.musicserver.mapper.CommentMapper;
import com.example.musicserver.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-04-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
