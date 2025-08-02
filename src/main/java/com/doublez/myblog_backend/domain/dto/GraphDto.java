package com.doublez.myblog_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class GraphDto {
    private List<GraphNodeDto> nodes;
    private List<GraphLinkDto> links;
}