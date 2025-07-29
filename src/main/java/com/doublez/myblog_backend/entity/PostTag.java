package com.doublez.myblog_backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("post_tags")
public class PostTag {
    private Integer postId;
    private Integer tagId;
}