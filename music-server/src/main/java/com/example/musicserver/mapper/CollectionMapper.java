package com.example.musicserver.mapper;

import com.example.musicserver.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

}
