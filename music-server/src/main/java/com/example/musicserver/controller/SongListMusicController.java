package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.musicserver.entity.SongListMusic;
import com.example.musicserver.service.ISongListMusicService;
import com.example.musicserver.utils.Consts;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌单中的歌曲 前端控制器
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/songListMusic")
public class SongListMusicController {
    @Resource
    private ISongListMusicService iSongListMusicService;

    /**
     * 给歌单添加歌曲
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addListSong(SongListMusic songListMusic){
        JSONObject jsonObject = new JSONObject();
        boolean flag = iSongListMusicService.save(songListMusic);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"保存失败");
        return jsonObject;

    }

    /**
     * 根据歌单id查询歌曲
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public List<SongListMusic> detail(Integer songListId){
        QueryWrapper<SongListMusic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return iSongListMusicService.list(queryWrapper);
    }

    /**
     * 删除歌单里的歌曲
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object delete(Integer songId,Integer songListId){
        QueryWrapper<SongListMusic> queryWrapper = new QueryWrapper<>();
        //删除XXX歌单的XXX歌曲
        queryWrapper.eq("song_list_id",songListId);
        queryWrapper.eq("song_id",songId);
        boolean flag = iSongListMusicService.remove(queryWrapper);
        return flag;
    }
    

}
