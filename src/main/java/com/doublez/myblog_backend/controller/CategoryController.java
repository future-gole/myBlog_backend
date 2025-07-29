package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.entity.Category;
import com.doublez.myblog_backend.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public List<String> getAllCategories() {
        return categoryService.list().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}