package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.doublez.myblog_backend.domain.vo.PostDetailVO;
import com.doublez.myblog_backend.domain.dto.PostRequestDto;
import com.doublez.myblog_backend.domain.vo.PostSimpleVO;
import com.doublez.myblog_backend.entity.*;
import com.doublez.myblog_backend.mapper.*;
import com.doublez.myblog_backend.service.ILinkService;
import com.doublez.myblog_backend.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<PostSimpleVO> getAllPosts() {
        return postMapper.selectAllPostSimpleDto();
    }

    @Override
    public PostDetailVO getPostById(Integer id) {
        Post post = postMapper.selectById(id);
        if (post == null) {
            throw new RuntimeException("文章未找到");
        }
        Category category = categoryMapper.selectById(post.getCategoryId());
        if(category == null) {
            throw new RuntimeException("分类未找到");
        }
        PostDetailVO postDetailDto = new PostDetailVO();

        List<PostTag> postTags = postTagMapper.selectList(new LambdaQueryWrapper<PostTag>().eq(PostTag::getPostId, post.getId()));
        if(postTags != null && !postTags.isEmpty()) {
            List<Integer> collect = postTags.stream().map(PostTag::getTagId).toList();
            List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<Tag>().in(Tag::getId, collect));
            List<String> TagNamelist = tags.stream().map(Tag::getName).toList();
            postDetailDto.setTags(TagNamelist);
        }

        postDetailDto.setId(post.getId());
        postDetailDto.setTitle(post.getTitle());
        postDetailDto.setContent(post.getContent());
        postDetailDto.setCreatedAt(post.getCreatedAt());
        postDetailDto.setUpdatedAt(post.getUpdatedAt());
        postDetailDto.setCategory(category.getName());

        return postDetailDto;
    }

    @Override
    @Transactional
    public PostDetailVO createPost(PostRequestDto request) {
        Category category = handleCategory(request.getCategory());

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