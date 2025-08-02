package com.doublez.myblog_backend.domain.vo;

import com.doublez.myblog_backend.domain.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class PostDetailVO extends BaseEntity {
    private Integer id;
    private String title;
    private String content;
    private String category;
    private List<String> tags;
}