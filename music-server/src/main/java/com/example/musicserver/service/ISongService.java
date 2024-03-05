package com.example.musicserver.service;

import com.example.musicserver.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 歌曲 服务类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-21
 */
public interface ISongService extends IService<Song> {
    /**
     *增加
     */
    public boolean insert(Song song);

    /**
     *修改
     */

//    public boolean update(Song song);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();

    /**
     * 根据歌名精确查询列表
     */
    public List<Song> songOfName(String gqName);

    /**
     * 根据歌名模糊查询列表
     */
//    public List<Song> likeSongOfName(String gqName);

    /**
     * 根据歌手id查询歌曲列表
     */
    public List<Song> songOfSingerId(Integer gqSingerId);

    /**
     * 根据歌曲 id 更新被点击数
     * @param songId
     * @return
     */
    public boolean updateClickCountById(Integer songId);
}