package com.doublez.myblog_backend.controller;

import com.doublez.myblog_backend.domain.dto.GraphDto;
import com.doublez.myblog_backend.service.IGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graph")
@RequiredArgsConstructor
public class GraphController {
    @Autowired
    private IGraphService graphService;

    @GetMapping
    public GraphDto getGraphData() {
        return graphService.getGraphData();
    }
}