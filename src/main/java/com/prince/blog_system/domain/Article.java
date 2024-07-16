package com.prince.blog_system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 * @TableName article
 */
@TableName(value ="article")
@Data
@Builder
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 
     */
    private Date created;

    /**
     * 
     */
    private Date lastModified;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}