package com.example.musicserver.mapper;

import com.example.musicserver.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 歌曲 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-21
 */
@Mapper
public interface SongMapper extends BaseMapper<Song> {
    /**
     *增加
     */
    @Override
    public int insert(Song song);

    /**
     *修改
     */


//    public int update(Song song);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询整个歌曲
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */

    public List<Song> allSong();

    /**
     * 根据歌曲id查询歌曲
     */

    /**
     * 根据歌名精确查询列表
     */

    public List<Song> songOfName(String gqName);

    /**
     * 根据歌名模糊查询列表
     */

//    public List<Song> likeSongOfName(String gqName);

    /**
     * 根据歌手id查询
     */

    public List<Song> songOfSingerId(Integer gqSingerId);


    /**
     * 根据歌曲 id 更新被点击数
     * @param songId
     * @return
     */
    public int updateClickCountById(Integer songId);


}
