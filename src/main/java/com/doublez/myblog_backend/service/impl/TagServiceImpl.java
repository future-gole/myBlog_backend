package com.doublez.myblog_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doublez.myblog_backend.entity.Tag;
import com.doublez.myblog_backend.mapper.TagMapper;
import com.doublez.myblog_backend.service.ITagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
}
