package com.example.musicserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.musicserver.entity.Song;
import com.example.musicserver.mapper.SongMapper;
import com.example.musicserver.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 歌曲 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-21
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {
    @Autowired
    private SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 修改
     *
     * @param song
     */
//    @Override
//    public boolean update(Song song) {
//        return songMapper.update(song)>0;
//    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询整个歌曲
     *
     * @param id
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        System.out.println(id);
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 根据歌名精确查询列表
     *
     * @param gqName
     */
    @Override
    public List<Song> songOfName(String gqName) {
        return songMapper.songOfName(gqName);
    }

    /**
     * 根据歌名模糊查询列表
     *
     * @param gqName
     */
//    @Override
//    public List<Song> likeSongOfName(String gqName) {
//        return songMapper.likeSongOfName("%"+gqName+"%");
//    }


    /**
     * 根据歌手id查询
     *
     * @param gqSingerId
     */
    @Override
    public List<Song> songOfSingerId(Integer gqSingerId) {
        return songMapper.songOfSingerId(gqSingerId);
    }

    /**
     * 根据歌曲 id 更新被点击数
     *
     * @param songId
     * @return
     */
    @Override
    public boolean updateClickCountById(Integer songId) {
        return songMapper.updateClickCountById(songId) > 0;
    }
}