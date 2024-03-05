package com.example.musicserver.service.impl;

import com.example.musicserver.entity.Admin;
import com.example.musicserver.mapper.AdminMapper;
import com.example.musicserver.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理员 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-02
 */
@Service
//@Service告诉controller层可以调用
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    /**
     * 验证密码是否正确
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifPassword(String username, String password) {
        return adminMapper.verifyPassword(username, password)>0;
    }
}
