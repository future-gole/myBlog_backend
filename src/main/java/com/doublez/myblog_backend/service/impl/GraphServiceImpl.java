package com.doublez.myblog_backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.doublez.myblog_backend.domain.dto.GraphDto;
import com.doublez.myblog_backend.domain.dto.GraphLinkDto;
import com.doublez.myblog_backend.domain.dto.GraphNodeDto;
import com.doublez.myblog_backend.entity.Category;
import com.doublez.myblog_backend.entity.Link;
import com.doublez.myblog_backend.entity.Post;
import com.doublez.myblog_backend.mapper.CategoryMapper;
import com.doublez.myblog_backend.mapper.LinkMapper;
import com.doublez.myblog_backend.mapper.PostMapper;
import com.doublez.myblog_backend.service.IGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GraphServiceImpl implements IGraphService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public GraphDto getGraphData() {
        List<Post> posts = postMapper.selectList(new LambdaQueryWrapper<Post>()
                .select(Post::getCategoryId, Post::getTitle, Post::getId));
        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .select(Category::getId, Category::getName));
        List<Link> links = linkMapper.selectList(new LambdaQueryWrapper<Link>()
                .select(Link::getTarget,Link::getSource));

        Map<Integer, String> idToNameMap = categories.stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));
        List<GraphNodeDto> grapNodeDto = new ArrayList<>();
        //循环存入grapDto对象
        for(Post post : posts) {
            GraphNodeDto graphNodeDto = new GraphNodeDto();
            graphNodeDto.setId(post.getId());
            graphNodeDto.setTitle(post.getTitle());
            graphNodeDto.setCategory(idToNameMap.get(post.getCategoryId()));
            grapNodeDto.add(graphNodeDto);
        }
        List<GraphLinkDto> graphLinkDtoList = BeanUtil.copyToList(links, GraphLinkDto.class);
        return new GraphDto(grapNodeDto,graphLinkDtoList);
    }
}