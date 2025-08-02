package com.doublez.myblog_backend.service;


import com.doublez.myblog_backend.domain.dto.PostRequestDto;
import com.doublez.myblog_backend.domain.vo.PostDetailVO;
import com.doublez.myblog_backend.domain.vo.PostSimpleVO;

import java.util.List;

public interface IPostService {
    List<PostSimpleVO> getAllPosts();

    PostDetailVO getPostById(Integer id);

    PostDetailVO createPost(PostRequestDto request);

}