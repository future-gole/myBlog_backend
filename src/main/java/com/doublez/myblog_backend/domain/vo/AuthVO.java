package com.doublez.myblog_backend.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证成功后返回给前端的数据传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO {
    private String token;
}
