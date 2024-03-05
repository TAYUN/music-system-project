package com.example.musicserver.service.impl;

import com.example.musicserver.entity.Singer;
import com.example.musicserver.mapper.SingerMapper;
import com.example.musicserver.service.ISingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-04
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {

    @Autowired

    private SingerMapper singerMapper;

    /**
     * 增加
     *
     * @param singer
     */
    @Override
    public boolean insert(Singer singer) {
        return this.singerMapper.insert(singer) > 0;
    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return this.singerMapper.selectByPrimaryKey(id)
        ;
    }

    /**
     * 查询所有歌手
     */
    @Override
    public List<Singer> allSinger() {
        return this.singerMapper.allSinger();

    }

    /**
     * 根据歌手名字模糊查询列表
     *
     * @param name
     */
    @Override
    public List<Singer> singerOfName(String name) {
        return null;
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerMapper.singerOfSex(sex);
    }
}
