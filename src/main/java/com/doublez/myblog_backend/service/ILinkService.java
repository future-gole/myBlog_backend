package com.doublez.myblog_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doublez.myblog_backend.entity.Link;
import org.apache.ibatis.annotations.Mapper;


public interface ILinkService  extends IService<Link> {
    void extractPost(String text, Integer postId);
}
