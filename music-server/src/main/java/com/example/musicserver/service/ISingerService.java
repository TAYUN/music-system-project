package com.example.musicserver.service;

import com.example.musicserver.entity.Singer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 歌手 服务类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-04
 */
public interface ISingerService extends IService<Singer> {

    /**
     *增加
     */
    public boolean insert(Singer singer);

    /**
     *修改
     */
    public boolean update(Singer singer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询列表
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Singer> singerOfSex(Integer sex);

}
