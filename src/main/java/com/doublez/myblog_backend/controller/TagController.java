package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.entity.Tag;
import com.doublez.myblog_backend.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    @Autowired
    private ITagService tagService;

//    @GetMapping
//    public List<String> getAllTags() {
//        return tagService.list().stream()
//                .map(Tag::getName)
//                .collect(Collectors.toList());
//    }
}