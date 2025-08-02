package com.doublez.myblog_backend.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 创建或更新文章时，从前端接收的数据传输对象
 */
@Data
public class PostRequestDto {
    private String title;
    private String category;
    private List<String> tagNames;
    private String featuredImageId;
    private String content;
}