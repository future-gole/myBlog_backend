package com.doublez.myblog_backend.domain;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(1000,"OK"),
    FAIL(2000,"FAIL"),

    USER_NOT_EXITS(2001,"用户不存在"),
    PASSWORD_ERROR(2002,"密码错误");
    int code;
    String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
