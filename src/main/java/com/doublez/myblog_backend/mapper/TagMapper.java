package com.doublez.myblog_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doublez.myblog_backend.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {}