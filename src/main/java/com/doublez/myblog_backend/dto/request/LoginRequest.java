package com.doublez.myblog_backend.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}