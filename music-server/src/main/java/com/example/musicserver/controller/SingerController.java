package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.musicserver.entity.Singer;
import com.example.musicserver.service.ISingerService;
import com.example.musicserver.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * 歌手 前端控制器
 * @author xiaoyun
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private ISingerService iSingerService;

    /**
     * 添加歌手
     * @return
     */
    //添加歌手
//    @PostMapping("/add")
//    public Object addSinger(Singer singer){
//        JSONObject jsonObject=new JSONObject();
//        boolean flag = iSingerService.insert(singer);
//        if(flag){//保存成功
//            jsonObject.put(Consts.CODE,1);
//            jsonObject.put(Consts.MSG,"添加成功");
//            return jsonObject;
//        }
//        jsonObject.put(Consts.CODE,0);
//        jsonObject.put(Consts.MSG,"添加失败");
//        return jsonObject;
//    }

    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String name  = request.getParameter("name").trim();  //姓名
        String sex  = request.getParameter("sex").trim();   //性别
        String pic  = request.getParameter("pic").trim();   //头像
        String birth  = request.getParameter("birth").trim();   //生日
        String location  = request.getParameter("location").trim();    //地区
        String introduction  = request.getParameter("introduction").trim();     //简介
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthDate = new Date();
////        在这抛异常这里面还可以用
//        try{
//            birthDate = dateFormat.parse(birth);
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        保存歌手到对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));//这里的sex是String需要进行强制类型转换
        singer.setPic(pic);
//        singer.setBirth(birthDate);
        singer.setBirth(birth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = iSingerService.insert(singer);
        if(flag){
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
            return jsonObject;
        }

    }

    /**
     * 修改歌手
     * @return
     */
    //修改歌手
    @PostMapping("/update")
    public Object editSinger(Singer singer){
        JSONObject jsonObject=new JSONObject();
        boolean flag = iSingerService.update(singer);
        if(flag){ //保存成功
            //  System.out.println("修改成功");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 根据主键删除歌手
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public Object deleteSinger(HttpServletRequest request){
        String id = request.getParameter("id").trim();  //主键
        boolean flag =iSingerService.delete(Integer.parseInt(id));
        return flag;

    }

    /**
     * 根据主键查询歌手
     * @param request
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByprimarykey(HttpServletRequest request){
        String id = request.getParameter("id").trim();  //主键

        return iSingerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有歌手
     * @return
     */
    @GetMapping("/allSinger")
    public Object allSinger(){
        return iSingerService.allSinger();
    }


    /**
     * 根据名字查询歌手
     * @param request
     * @return
     */
    @PostMapping("/singerOfName")
    public Object singerOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return iSingerService.singerOfName("%" + name + "%");
    }

    /**
     * 根据性别查询歌手
     * @param request
     * @return
     */
    @GetMapping("/singerOfSex")
    public Object singerOfSex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();
        return iSingerService.singerOfSex(Integer.parseInt(sex));
    }


    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) {
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
                + System.getProperty("file.separator") + "singerPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = iSingerService.update(singer);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败了");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }


}
