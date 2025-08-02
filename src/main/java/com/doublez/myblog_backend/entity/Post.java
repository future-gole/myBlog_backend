package com.doublez.myblog_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.doublez.myblog_backend.domain.BaseEntity;
import lombok.Data;

@Data
@TableName("posts")
public class Post extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer categoryId;
    private String imageUrl;
}