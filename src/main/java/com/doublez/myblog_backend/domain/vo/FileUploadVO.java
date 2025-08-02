package com.doublez.myblog_backend.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadVO {
    private Integer imageId;
    private String imageUrl;
}