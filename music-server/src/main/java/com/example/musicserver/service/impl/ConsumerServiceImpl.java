package com.example.musicserver.service.impl;

import com.example.musicserver.entity.Consumer;
import com.example.musicserver.mapper.ConsumerMapper;
import com.example.musicserver.service.IConsumerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前端用户管理 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements IConsumerService {

}
