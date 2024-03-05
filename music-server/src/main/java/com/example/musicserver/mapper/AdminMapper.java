package com.example.musicserver.mapper;

import com.example.musicserver.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 后台管理员 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-02
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 验证密码是否正确
     * @param username 用户名
     * @param password  密码
     * @return  用户
     */

    public int verifyPassword(String username, String password);

}
