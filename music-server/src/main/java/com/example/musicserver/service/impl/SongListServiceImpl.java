package com.example.musicserver.service.impl;

import com.example.musicserver.entity.SongList;
import com.example.musicserver.mapper.SongListMapper;
import com.example.musicserver.mapper.SongMapper;
import com.example.musicserver.service.ISongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单 服务实现类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements ISongListService {
    @Autowired
    private SongListMapper songListMapper;
    /**
     * 根据歌曲 id 更新被点击数
     * @param id
     * @return
     */
    @Override
    public boolean updateClickCountById(Integer id) {
        return songListMapper.updateClickCountById(id) > 0;
    }
}
