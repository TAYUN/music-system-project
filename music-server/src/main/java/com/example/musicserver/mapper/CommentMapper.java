package com.example.musicserver.mapper;

import com.example.musicserver.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-04-05
 */

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
