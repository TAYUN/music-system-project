package com.example.musicserver.service;

import com.example.musicserver.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台管理员 服务类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-02
 */
public interface IAdminService extends IService<Admin> {

    /**
     *   验证密码是否正确
     * @param username
     * @param password
     * @return
     */

    public boolean verifPassword(String username, String password);

}
