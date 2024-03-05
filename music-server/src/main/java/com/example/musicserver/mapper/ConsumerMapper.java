package com.example.musicserver.mapper;

import com.example.musicserver.entity.Consumer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 前端用户管理 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
@Mapper
public interface ConsumerMapper extends BaseMapper<Consumer> {

}
