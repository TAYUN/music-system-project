package com.example.musicserver.service;

import com.example.musicserver.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 歌单 服务类
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
public interface ISongListService extends IService<SongList> {
    /**
     * 根据歌单 id 更新被点击数
     * @param id
     * @return
     */
    public boolean updateClickCountById(Integer id);
}
