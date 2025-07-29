package com.doublez.myblog_backend.service;

import com.doublez.myblog_backend.dto.response.PostDetailDto;
import com.doublez.myblog_backend.dto.response.PostSimpleDto;
import com.doublez.myblog_backend.dto.request.PostRequestDto;

import java.util.List;

public interface IPostService {
    List<PostSimpleDto> getAllPosts();

    PostDetailDto getPostById(Integer id);

    PostDetailDto createPost(PostRequestDto request);

}