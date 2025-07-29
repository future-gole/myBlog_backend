package com.doublez.myblog_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {
    private Integer imageId;
    private String imageUrl;
}