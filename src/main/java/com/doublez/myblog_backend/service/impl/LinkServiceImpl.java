package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doublez.myblog_backend.entity.Category;
import com.doublez.myblog_backend.entity.Link;
import com.doublez.myblog_backend.entity.Post;
import com.doublez.myblog_backend.mapper.CategoryMapper;
import com.doublez.myblog_backend.mapper.LinkMapper;
import com.doublez.myblog_backend.mapper.PostMapper;
import com.doublez.myblog_backend.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper,Link> implements ILinkService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    private   List<String> extractLinks(String text) {
        // 创建一个列表来存储提取出的链接内容
        List<String> foundLinks = new ArrayList<>();

        // 定义我们的正则表达式
        // \[\[  -> 匹配字面的 [[
        // (.+?) -> 捕获组1：匹配一个或多个任意字符，非贪婪模式
        // \]\]  -> 匹配字面的 ]]
        String regex = "\\[\\[(.+?)\\]\\]";

        // 编译正则表达式以提高性能
        Pattern pattern = Pattern.compile(regex);

        // 创建一个 Matcher 对象，用于在输入文本中查找匹配项
        Matcher matcher = pattern.matcher(text);

        // 循环查找所有匹配项
        while (matcher.find()) {
            // matcher.group(0) 会返回整个匹配的字符串，例如 "[[页面标题]]"
            // matcher.group(1) 会返回第一个捕获组的内容，也就是我们想要的 "页面标题"
            String linkContent = matcher.group(1);
            foundLinks.add(linkContent.trim()); // 使用 trim() 去除可能存在的前后空格
        }

        return foundLinks;
    }
    @Override
    public void extractPost(String text, Integer postId) {
        //删除原本的链接
        removeById(postId);
        //查询文章链接
        List<String> categoryAndTitles = extractLinks(text);
        if(categoryAndTitles.isEmpty()) return;
        List<String> categories = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for(String categoryAndTitle : categoryAndTitles){
            String[] split = categoryAndTitle.split("/");
            categories.add(split[0]);
            titles.add(split[1]);
        }
        List<Integer> categoriesId = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .in(Category::getName, categories)).stream().map(Category::getId).toList();
        List<Post> postIds = postMapper.selectList(new LambdaQueryWrapper<Post>()
                .select(Post::getId)
                .in(Post::getTitle, titles)
                .in(Post::getCategoryId,categoriesId));
        List<Link> linkList = new ArrayList<>();
        for(Post post : postIds){
            Link link = new Link();
            link.setSource(postId);
            link.setTarget(post.getId());
            linkList.add(link);
        }
        //保存链接
        saveBatch(linkList);
    }
}
