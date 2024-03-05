package com.example.musicserver.service.impl;

import com.example.musicserver.entity.Collection;
import com.example.musicserver.mapper.CollectionMapper;
import com.example.musicserver.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

}
