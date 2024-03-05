package com.example.musicserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 歌曲
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-21
 */
//@Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
//@AllArgsConstructor ： 注在类上，提供类的全参构造
//@NoArgsConstructor ： 注在类上，提供类的无参构造
//@Setter ： 注在属性上，提供 set 方法
//@Getter ： 注在属性上，提供 get 方法
//@EqualsAndHashCode ： 注在类上，提供对应的 equals 和 hashCode 方法
//@Log4j/@Slf4j ： 注在类上，提供对应的 Logger 对象，变量名为 log
//@Accessors(chain = true)//链式set方法
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手id
     */
    private Integer gqSingerId;

    /**
     * 歌名
     */
    private String gqName;

    /**
     * 歌曲简介
     */
    private String gqIntroduction;

    /**
     * 创建时间
     */
    @TableField(value = "gq_create_time", fill = FieldFill.INSERT)

    private Date gqCreateTime;

    /**
     * 更新时间
     */
    @TableField(value = "gq_update_time", fill = FieldFill.INSERT_UPDATE)

    private Date gqUpdateTime;

    /**
     * 歌曲图片
     */
    private String gqPic;

    /**
     * 歌词
     */
    private String gqLyric;

    /**
     * 歌词地址
     */
    private String gqUrl;

    /**
     * 歌曲点击数
     */
    private int clickCount;


}
