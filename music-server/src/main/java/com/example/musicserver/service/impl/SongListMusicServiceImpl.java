package com.example.musicserver.service.impl;

import com.example.musicserver.entity.SongListMusic;
import com.example.musicserver.mapper.SongListMusicMapper;
import com.example.musicserver.service.ISongListMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单中的歌曲 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@Service
public class SongListMusicServiceImpl extends ServiceImpl<SongListMusicMapper, SongListMusic> implements ISongListMusicService {

}
