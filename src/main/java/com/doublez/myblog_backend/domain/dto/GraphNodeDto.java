package com.doublez.myblog_backend.domain.dto;

import lombok.Data;

/**
 * 关系图谱中的“节点”模型
 */
@Data
public class GraphNodeDto {
    private Integer id;
    private String title;
    private String category;
}