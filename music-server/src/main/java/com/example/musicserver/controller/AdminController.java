package com.example.musicserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.musicserver.service.IAdminService;
import com.example.musicserver.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录 前端控制器
 *
 * @author xiaoyun
 * @since 2023-03-02
 */
//@CrossOrigin
@Configuration
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;

    /**
     * 验证登录是否成功
     */
    @PostMapping("/login/status")
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = iAdminService.verifPassword(name, password);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.NAME, name);
            jsonObject.put(Consts.MSG, "登录成功");
            session.setAttribute(Consts.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.NAME, name);
        jsonObject.put(Consts.MSG, "用户名或密码错误");
        return jsonObject;
    }


}
