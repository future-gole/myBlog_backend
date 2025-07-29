package com.doublez.myblog_backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("links")
public class Link {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer source;
    private Integer target;
    private LocalDateTime createdAt;
}