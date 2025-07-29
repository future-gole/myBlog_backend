package com.doublez.myblog_backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String saveImage(MultipartFile file, Long articleId);
}