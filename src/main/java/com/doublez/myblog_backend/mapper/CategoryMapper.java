package com.doublez.myblog_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doublez.myblog_backend.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {}