package com.doublez.myblog_backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.doublez.myblog_backend.dto.dto.GraphDto;
import com.doublez.myblog_backend.dto.dto.GraphLinkDto;
import com.doublez.myblog_backend.dto.dto.GraphNodeDto;
import com.doublez.myblog_backend.entity.Category;
import com.doublez.myblog_backend.entity.Link;
import com.doublez.myblog_backend.entity.Post;
import com.doublez.myblog_backend.mapper.CategoryMapper;
import com.doublez.myblog_backend.mapper.LinkMapper;
import com.doublez.myblog_backend.mapper.PostMapper;
import com.doublez.myblog_backend.service.IGraphService;
import com.fasterxml.jackson.core.type.TypeReference;
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
        List<Link> links = linkMapper.selectList(new LambdaQueryWrapper<Link>());

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
//    @Override
//    public GraphDto getGraphData() {
//        // 1. 使用 MyBatis-Plus 基础方法，一次性获取所有需要的数据
//        List<Post> allPosts = postMapper.selectList(null);
//        List<Category> allCategories = categoryMapper.selectList(null);
//        List<Link> allLinks = linkMapper.selectList(null);
//
//        // 2. 创建高效的查找表 (Map)，避免在循环中重复查询数据库
//        Map<Integer, Category> categoryMap = allCategories.stream()
//                .collect(Collectors.toMap(Category::getId, category -> category));
//
//        Map<Integer, String> postIdToCompositeSlugMap = allPosts.stream()
//                .collect(Collectors.toMap(
//                        Post::getId,
//                        post -> {
//                            Category category = categoryMap.get(post.getCategoryId());
//                            String categorySlug = (category != null) ? category.getSlug() : "uncategorized";
//                            return categorySlug + "/" + post.getSlug();
//                        }
//                ));
//
//        // 3. 在内存中组装 Nodes (节点) 数据
//        List<GraphNodeDto> nodes = allPosts.stream().map(post -> {
//            GraphNodeDto nodeDto = new GraphNodeDto();
//            Category category = categoryMap.get(post.getCategoryId());
//
//            nodeDto.setId(postIdToCompositeSlugMap.get(post.getId()));
//            nodeDto.setTitle(post.getTitle());
//            nodeDto.setCategory(category != null ? category.getName() : "未分类");
//            return nodeDto;
//        }).collect(Collectors.toList());
//
//        // 4. 在内存中组装 Links (连接线) 数据
//        List<GraphLinkDto> links = allLinks.stream()
//                .map(link -> new GraphLinkDto(
//                        postIdToCompositeSlugMap.get(link.getSourcePostId()),
//                        postIdToCompositeSlugMap.get(link.getTargetPostId())
//                ))
//                // 过滤掉可能因文章删除而产生的无效链接
//                .filter(dto -> dto.getSource() != null && dto.getTarget() != null)
//                .collect(Collectors.toList());
//
//        // 5. 封装成最终的 DTO 并返回
//        GraphDto graphDto = new GraphDto();
//        graphDto.setNodes(nodes);
//        graphDto.setLinks(links);
//        return graphDto;
//    }
}