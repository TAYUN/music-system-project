package com.example.musicserver.mapper;

import com.example.musicserver.entity.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 歌手 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-04
 */
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {
    /**
     *增加
     */
    public int insert(Singer singer);

    /**
     *修改
     */
    public int update(Singer singer);

    /**
     * 删除
     */
    public int delete(Integer id);

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
    public List<Singer> singerOfSex(Integer sex);//这里是


}
