package com.doublez.myblog_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doublez.myblog_backend.domain.vo.PostSimpleVO;
import com.doublez.myblog_backend.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    List<PostSimpleVO> selectAllPostSimpleDto();
}