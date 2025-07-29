package com.doublez.myblog_backend.dto.response;

import com.doublez.myblog_backend.dto.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class PostDetailDto extends BaseEntity {
    private Integer id;
    private String title;
    private String content;
    private String category;
    private List<String> tags;
}