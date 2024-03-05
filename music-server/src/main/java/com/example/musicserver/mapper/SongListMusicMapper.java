package com.example.musicserver.mapper;

import com.example.musicserver.entity.SongListMusic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 歌单中的歌曲 Mapper 接口
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@Mapper
public interface SongListMusicMapper extends BaseMapper<SongListMusic> {

}
