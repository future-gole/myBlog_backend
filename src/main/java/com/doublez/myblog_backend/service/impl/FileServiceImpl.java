package com.doublez.myblog_backend.service.impl;

import com.doublez.myblog_backend.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.nio.file.Path;
@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public String saveImage(MultipartFile file, Long articleId) {
        try {
            // 1. 校验文件是否为空
            if (file.isEmpty()) {
                log.error("文件为空");
            }
            // 2. 根据文章ID创建目录路径
            Path articleDirectory = Paths.get(uploadDir, String.valueOf(articleId));
            if (Files.notExists(articleDirectory)) {
                Files.createDirectories(articleDirectory);
            }

            // 3. 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String uniqueFileName = UUID.randomUUID().toString().replace("-","") + "." + fileExtension;

            // 4. 构建最终的文件保存路径
            Path targetLocation = articleDirectory.resolve(uniqueFileName);

            // 5. 保存文件
            file.transferTo(targetLocation.toFile());

            // 返回文件的相对路径或可访问的URL
            String relativePath = Paths.get(String.valueOf(articleId), uniqueFileName).toString();

            // 统一使用 / 作为路径分隔符
            return relativePath.replace("\\", "/");

        } catch (IOException ex) {
            // 提供更具体的错误信息
            log.error("文件上传失败，文章ID: {}, 错误: {}", articleId, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}