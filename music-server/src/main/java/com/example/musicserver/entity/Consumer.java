package com.example.musicserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 前端用户管理
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：1男，0女
     */
    private Boolean sex;

    /**
     * 联系方式
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")//date字符串转Date类型
    private Date birth;

    /**
     * 个签
     */
    private String introduction;

    /**
     * 地区
     */
    private String location;

    /**
     * 头像
     */
    private String avator;

    /**
     * 创建时间
     *
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;

    /**
     * 更新时间
     * 设置自动更新
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)

    private Date updateTime;


}
