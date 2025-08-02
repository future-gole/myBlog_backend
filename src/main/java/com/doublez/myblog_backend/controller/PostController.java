package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.domain.vo.PostDetailVO;
import com.doublez.myblog_backend.domain.dto.PostRequestDto;
import com.doublez.myblog_backend.domain.vo.PostSimpleVO;
import com.doublez.myblog_backend.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public List<PostSimpleVO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDetailVO getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public PostDetailVO createPost(@RequestBody PostRequestDto request) {
        return postService.createPost(request);
    }
}