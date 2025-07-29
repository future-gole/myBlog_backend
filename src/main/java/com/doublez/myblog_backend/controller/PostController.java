package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.dto.response.PostDetailDto;
import com.doublez.myblog_backend.dto.response.PostSimpleDto;
import com.doublez.myblog_backend.dto.request.PostRequestDto;
import com.doublez.myblog_backend.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public List<PostSimpleDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // API 端点已修正为通过 ID 查询
    @GetMapping("/{id}")
    public PostDetailDto getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public PostDetailDto createPost(@RequestBody PostRequestDto request) {
        return postService.createPost(request);
    }
}