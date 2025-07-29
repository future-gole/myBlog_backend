package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {
    @Autowired
    private IFileService fileService;


    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file,Long articleId) {

        return fileService.saveImage(file,articleId);
    }
}
