package com.doublez.myblog_backend.domain;

import lombok.Data;

@Data
public class R<T> {
    private int code;
    private String Msg;
    private T data;

    public static <T> R<T> ok(T data) {
        return assembleResult(data,ResultCode.SUCCESS);
    }
    public static <T> R<T> fail(ResultCode resultCode) {
        return assembleResult(null, resultCode);
    }
    private static <T> R<T> assembleResult(T data, ResultCode resultCode) {
        R<T> r = new R<T>();
        r.setCode(resultCode.getCode());
        r.setMsg(resultCode.getMsg());
        r.setData(data);
        return r;
    }
}
