package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.dto.request.LoginRequest;
import com.doublez.myblog_backend.dto.response.AuthResponse;
import com.doublez.myblog_backend.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    @Autowired
//    private IAuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
//        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
//}