package com.example.musicserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 歌单
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */

//@Accessors(chain = true)// 不写默认为false，当该值为 true 时，对应字段的 setter 方法调用后，会返回当前对象。

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SongList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 歌单标题
     */
    private String title;

    /**
     * 歌单图片
     */
    private String pic;

    /**
     * 歌单简介
     */
    private String introduction;

    /**
     * 歌单风格
     */
    private String style;

    /**
     * 歌单被点击数
     */
    private int clickCount;

}
