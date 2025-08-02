package com.doublez.myblog_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.util.Base64;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadDir + "**")
                .addResourceLocations("file:" + uploadDir);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/post/create");
    }

    public static void main(String[] args) {
        // 1. 创建一个密码学安全的随机数生成器实例
        SecureRandom secureRandom = new SecureRandom();

        // 2. 创建一个字节数组来存放密钥。对于 HS512，需要 64 字节。
        byte[] keyBytes = new byte[64];

        // 3. 用安全的随机字节填充数组
        secureRandom.nextBytes(keyBytes);

        // 4. 将随机字节编码为 Base64 字符串，使其更易于存储和传输
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);

        System.out.println("Generated HS512 Secret Key (Base64 Encoded):");
        System.out.println(secretKey);
    }
}