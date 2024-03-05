package com.example.musicserver.mapper;

import com.example.musicserver.entity.SongList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 歌单 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@Mapper
public interface SongListMapper extends BaseMapper<SongList> {
    /**
     * 根据歌单 id 更新被点击数
     * @param id
     * @return
     */
    @Update("update song_list set click_count = click_count + 1 where id = #{id}")
    public int updateClickCountById(@Param("id") Integer id);
}
