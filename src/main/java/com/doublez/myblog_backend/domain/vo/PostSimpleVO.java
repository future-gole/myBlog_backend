package com.doublez.myblog_backend.domain.vo;

import lombok.Data;

@Data
public class PostSimpleVO {
    private Integer id;
    private String title;
    private String date;
    private String category;
    private String featuredImageUrl; // 从 imageUrl 改为 featuredImageUrl
}