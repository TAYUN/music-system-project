package com.example.musicserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 歌单中的歌曲
 * </p>
 *
 * @author xiaoyun
 * @since 2023-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SongListMusic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 歌单id	
     */
    private Integer songListId;


}
