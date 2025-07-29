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
    private Integer source;
    private Integer target;
}
