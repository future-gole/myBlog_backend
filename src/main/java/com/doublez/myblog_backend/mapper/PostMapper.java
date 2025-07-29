package com.doublez.myblog_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doublez.myblog_backend.dto.response.PostDetailDto;
import com.doublez.myblog_backend.dto.response.PostSimpleDto;
import com.doublez.myblog_backend.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    List<PostSimpleDto> selectAllPostSimpleDto();
}