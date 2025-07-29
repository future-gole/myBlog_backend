package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.doublez.myblog_backend.dto.response.PostDetailDto;
import com.doublez.myblog_backend.dto.response.PostSimpleDto;
import com.doublez.myblog_backend.dto.request.PostRequestDto;
import com.doublez.myblog_backend.entity.*;
import com.doublez.myblog_backend.mapper.*;
import com.doublez.myblog_backend.service.ILinkService;
import com.doublez.myblog_backend.service.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagMapper postTagMapper;

    @Autowired
    private ILinkService linkService;


    @Override
    public List<PostSimpleDto> getAllPosts() {
        return postMapper.selectAllPostSimpleDto();
    }

    @Override
    public PostDetailDto getPostById(Integer id) {
        Post post = postMapper.selectById(id);
        if (post == null) {
            throw new RuntimeException("文章未找到");
        }
        Category category = categoryMapper.selectById(post.getCategoryId());
        if(category == null) {
            throw new RuntimeException("分类未找到");
        }
        List<PostTag> postTags = postTagMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId()));
        PostDetailDto postDetailDto = new PostDetailDto();
        postDetailDto.setId(post.getId());
        postDetailDto.setTitle(post.getTitle());
        postDetailDto.setContent(post.getContent());
        postDetailDto.setCategory(category.getName());
        postDetailDto.setTags(postTags.stream().map(PostTag::toString).collect(Collectors.toList()));
        return postDetailDto;
    }

    @Override
    @Transactional
    public PostDetailDto createPost(PostRequestDto request) {
        Category category = handleCategory(request.getCategoryName());

        Long count = postMapper.selectCount(new LambdaQueryWrapper<Post>()
                .eq(Post::getTitle, request.getTitle())
                .eq(Post::getCategoryId, category.getId()));
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategoryId(category.getId());
        post.setCreatedAt(LocalDateTime.now());
        if(count > 0) {
            postMapper.updateById(post);
        }else {
            postMapper.insert(post);
        }
        //处理标签
        handleTags(request.getTagNames(), post.getId());
        //处理链接
        linkService.extractPost(request.getContent(), post.getId());

        return getPostById(post.getId());
    }

    private Category handleCategory(String categoryName) {
        Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, categoryName));
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            categoryMapper.insert(category);
        }
        return category;
    }

    private void handleTags(List<String> tagNames, Integer postId) {
        if (tagNames == null || tagNames.isEmpty()) return;
        for (String tagName : tagNames) {
            Tag tag = tagMapper.selectOne(new LambdaQueryWrapper<Tag>()
                        .eq(Tag::getName, tagName));
            //判断tag是否存在
            //不存在创建
            if (tag == null) {
                tag = new Tag();
                tag.setName(tagName);
                tagMapper.insert(tag);
            }
            //存在直接插入
            PostTag postTag = new PostTag();
            postTag.setPostId(postId);
            postTag.setTagId(tag.getId());
            postTagMapper.insert(postTag);
        }
    }
}