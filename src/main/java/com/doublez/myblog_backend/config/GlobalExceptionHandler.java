package com.doublez.myblog_backend.config;

import com.doublez.myblog_backend.domain.R;
import com.doublez.myblog_backend.domain.ResultCode;
import com.doublez.myblog_backend.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public R<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        ResultCode resultCode = e.getResultCode();
        log.error("请求地址'{}',发生业务异常:{}", requestURI, e.getMessage(),e);
        return R.fail(resultCode);
    }
}