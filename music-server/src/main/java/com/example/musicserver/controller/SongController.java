package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.musicserver.entity.Song;
import com.example.musicserver.mapper.SongMapper;
import com.example.musicserver.service.ISongService;
import com.example.musicserver.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 歌曲 前端控制器
 * @author xiaoyun
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ISongService iSongService;

    /**
     * 上传歌曲
     * @param song
     * @param mpFile
     * @return
     */
    @PostMapping("/add")
    public Object addSong(Song song, @RequestParam("file") MultipartFile mpFile) {
        JSONObject jsonObject = new JSONObject();
        //上传歌曲文件,文件为空，上传失败
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "歌曲上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径，歌曲存储路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/" + fileName;


        try {
            mpFile.transferTo(dest);
            song.setGqUrl(storeUrlPath);
            song.setGqPic("/img/songPic/default.png");
            System.out.println(song);
            Boolean flag = iSongService.insert(song);
            System.out.println(flag);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "添加成功");
                jsonObject.put("avator", storeUrlPath);
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    /**
     * 根据歌手id查询歌曲,返回一个歌曲列表
     * 为什么用mybatis出问题了
     */
    @GetMapping("/singer/detail")
    public Object songOfSingerId(Integer singerId) {
        return iSongService.songOfSingerId(singerId);
    }
//    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
//    public Object songOfSingerId(HttpServletRequest request) {
//        String singerId = request.getParameter("singerId");
//        return iSongService.songOfSingerId(Integer.parseInt(singerId));
//    }

    /**
     * 修改歌曲信息
     */
    @PostMapping("/update")
    public Object updateSong(Song song) {
        JSONObject jsonObject = new JSONObject();

        //lambda构造器
        LambdaUpdateWrapper<Song> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Song::getId, song.getId())
                .set(Song::getGqName, song.getGqName())
                .set(Song::getGqIntroduction, song.getGqIntroduction())
                .set(Song::getGqLyric, song.getGqLyric());

        boolean flag = iSongService.update(null, lambdaUpdateWrapper);//调用mybatis-plus封装的方法,第二个参数是条件
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 根据主键删除歌曲
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSong(Integer id) {
        //-TODO 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        boolean flag = iSongService.removeById(id);
        return flag;
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        //如果文件是空的，直接返回
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/" + fileName;
        try {
            avatorFile.transferTo(dest);//上传
            Song song = new Song();
            song.setId(id);
            song.setGqPic(storeAvatorPath);
            boolean flag = iSongService.updateById(song);//调用mybatis-plus封装的方法
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
     * 更新歌曲
     */
    @PostMapping("/updateSongUrl")
    public Object updateSongUrl(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        //-TODO 先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/song/" + fileName;
        try {
            avatorFile.transferTo(dest);
            //把歌曲id为目标id的歌曲改成指定歌曲，其他属性不变
            // lambda构造器
//            LambdaUpdateWrapper<Song> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
//            lambdaUpdateWrapper.eq(Song::getId, id)
//                    .set(Song::getGqUrl,storeAvatorPath);
//            boolean flag = iSongService.update(null,lambdaUpdateWrapper);//调用mybatis-plus封装的方法,第二个参数是条件

            UpdateWrapper<Song> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            Song song = new Song();
            song.setId(id);
            song.setGqUrl(storeAvatorPath);
            boolean flag = iSongService.update(song, updateWrapper);//调用mybatis-plus内置的方法，第二个参数是条件
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avator", storeAvatorPath);
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
     * 查询所有歌曲
     */
    @GetMapping("/allSong")
    public Object allSong() {
        return iSongService.list(null);
    }

    /**
     * 根据歌曲id查询指定歌曲
     * 为什么这个写法不行
     */
//    @PostMapping("/detail")
//    public Object songOfSongId(Integer id) {
//        return iSongService.selectByPrimaryKey(id);
//    }
    @GetMapping("/detail")
    public Object songOfSongId(Integer songId) {
        return iSongService.selectByPrimaryKey(songId);
    }
//    public Object songOfSongId(HttpServletRequest request) {
//        String songId = request.getParameter("songId").trim();  //主键
//        return iSongService.selectByPrimaryKey(Integer.parseInt(songId));
//    }

    /**
     * 根据歌曲名称模糊查询歌曲
     */
    @GetMapping("/likeSongOfName")
    public Object likeSongOfName(String gqName) {
        // 歌曲名
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("gq_name", gqName);
        // 调用内置的懒得写了
        return iSongService.list(queryWrapper);
    }

    /**
     * 根据歌曲名称精确查询歌曲
     */
//    @GetMapping("/songOfSongName")
//    public Object songOfSongName(String gqName) {
//        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("gq_name", gqName);
//        return iSongService.list(queryWrapper);
//    }
//    public List<Song> songOfSongName(String gqName) {
//        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("gq_name", gqName);
//        return iSongService.list(queryWrapper);
//    }

    /**
     * 根据歌曲 id 更新被点击数
     * @param songId
     * @return
     */
    @GetMapping("/clickSong")
    public Boolean clickSongById(Integer songId) {
        return iSongService.updateClickCountById(songId);
    }




}
