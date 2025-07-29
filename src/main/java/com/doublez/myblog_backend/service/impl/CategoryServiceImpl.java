package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doublez.myblog_backend.entity.Category;
import com.doublez.myblog_backend.mapper.CategoryMapper;
import com.doublez.myblog_backend.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}
