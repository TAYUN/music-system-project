package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.musicserver.entity.Consumer;
import com.example.musicserver.entity.SongList;
import com.example.musicserver.service.ISongListService;
import com.example.musicserver.utils.Consts;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌单 前端控制器
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Resource
    private ISongListService iSongListService;
    /**
     * 添加歌单
     */
    @PostMapping("/add")
    public Object addSongList(SongList songList) {
        JSONObject jsonObject = new JSONObject();
        songList.setPic("/img/songListPic/default.png");
        //根据title判断是否已存在
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", songList.getTitle());
        SongList songList1 = iSongListService.getOne(queryWrapper);
        System.out.println(songList1+ "===========");
        if (songList1 == null) {
            boolean flag = iSongListService.save(songList);
            if(flag){
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "歌单创建成功");
                return jsonObject;
            }else{
                jsonObject.put(Consts.CODE, -1);
                jsonObject.put(Consts.MSG, "未知错误");
                return jsonObject;
            }


        }
//        songList.setPic("/img/songListPic/default.png");
//        boolean flag = iSongListService.save(songList);
//        if (flag) {   //保存成功
//            jsonObject.put(Consts.CODE, 1);
//            jsonObject.put(Consts.MSG, "添加成功");
//            return jsonObject;
//        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "该歌单已经存在");
        return jsonObject;
    }

    /**
     * 修改歌单
     */
    @PostMapping("/update")
    public Object updateSongList(SongList songList) {
        JSONObject jsonObject = new JSONObject();
        boolean flag = iSongListService.updateById(songList);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }


    /**
     * 根据主键删除歌单
     */
    @GetMapping("/delete")
    public Object deleteSongList(Integer id) {
        boolean mark = iSongListService.removeById(id);
        return mark;
    }

    /**
     * 根据用户id删除
     *
     * @param userId
     * @return
     */
    @GetMapping("/delSongListByUserId")

    public Object delSongListByUserId(String userId) {
        JSONObject jsonObject = new JSONObject();

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        boolean flag = iSongListService.remove(wrapper);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "删除成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "删除失败");
        return jsonObject;
    }


    /**
     * 根据主键查询整个对象
     */
//    @RequestMapping(value = "/detail", method = RequestMethod.GET)
//    public Object selectByPrimaryKey(Integer id) {
//
//        return iSongListService.getById(id);
//    }

    /**
     * 根据主键查询整个对象！！TODO
     */
    @GetMapping("/detail")
    public Object getSongListOfId(HttpServletRequest request) {
        String id = request.getParameter("playlistId").trim();  //主键
        return iSongListService.getById(Integer.parseInt(id));
    }

    /**
     * 查询所有歌单
     */
    @RequestMapping(value = "/allSongList", method = RequestMethod.GET)
    public Object allSongList() {
        return iSongListService.list();//查询所有
    }

    /**
     * 根据标题精确查询歌单列表
     */
    @RequestMapping(value = "/songListOfTitle", method = RequestMethod.GET)
    public List<SongList> songListOfName(String title) {//歌单标题
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        return iSongListService.list(queryWrapper);
    }

    /**
     * 根据标题模糊查询歌单列表
     */
    @RequestMapping(value = "/likeTitle", method = RequestMethod.GET)
    public Object likeTitle(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title);
        return iSongListService.list(queryWrapper);
    }

    /**
     * 根据风格模糊查询歌单列表
     */
    @RequestMapping(value = "/likeStyle", method = RequestMethod.GET)
    public Object likeStyle(String style) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style", style);
        return iSongListService.list(queryWrapper);
    }

    /**
     * 根据用户查询歌单列表
     */
    @RequestMapping(value = "/userSongList", method = RequestMethod.GET)
    public Object getSongListByUserId(Integer userId) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return iSongListService.list(queryWrapper);
    }

    /**
     * 更新歌单图片
     */
    @RequestMapping(value = "/updateSongListPic", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = iSongListService.updateById(songList);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }

    /**
     * 根据歌单 id 更新被点击数
     * @param songListId
     * @return
     */
    @GetMapping("/clickSongList")
    public Boolean clickSongListById(Integer songListId) {
        return iSongListService.updateClickCountById(songListId);
    }


}
