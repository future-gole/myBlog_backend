package com.doublez.myblog_backend.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关系图谱中的“连接线”模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphLinkDto {
    private String source; // 源节点 id (组合 slug)
    private String target; // 目标节点 id (组合 slug)
}
