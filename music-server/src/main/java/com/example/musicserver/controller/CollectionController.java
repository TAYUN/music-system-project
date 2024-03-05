package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.musicserver.entity.Collection;
import com.example.musicserver.service.ICollectionService;
import com.example.musicserver.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏表 前端控制器
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
//@EqualsAndHashCode注解的作用就是自动实现model类的equals方法和hashcode方法。
@RestController
@RequestMapping("/collection")
public class CollectionController {

//    该类准备弃用
    @Autowired
    private ICollectionService iCollectionService;

    /**
     * 添加收藏
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addCollect(Collection collection){
        JSONObject jsonObject = new JSONObject();
        if(collection.getSongId()==null||collection.getSongId().equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏歌曲为空");
            return jsonObject;
        }
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_id",collection.getSongId());
        queryWrapper.eq("user_id",collection.getUserId());
        if(iCollectionService.getOne(queryWrapper)!=null){//用户已收藏该歌曲
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }

        boolean flag = iCollectionService.save(collection);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId");           //用户id
        String songId = request.getParameter("songId");           //歌曲id
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        boolean flag = iCollectionService.remove(queryWrapper);
        return flag;
    }

    /**
     * 查询所有收藏
     */
    @RequestMapping(value = "/allCollect",method = RequestMethod.GET)
    public Object allCollect(){
        return iCollectionService.list();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @RequestMapping(value = "/collectOfUserId",method = RequestMethod.GET)
    public Object collectOfUserId(Integer userId){
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return iCollectionService.list(queryWrapper);
    }
//    /**
//     * 查询某个用户的收藏歌单
//     */
//    @RequestMapping(value = "/collectPlaylistOfUserId",method = RequestMethod.GET)
        //    public Object collectPlaylistOfUserId(Integer userId){
//        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id",userId);
//        return iCollectionService.list(queryWrapper);
//    }


}

