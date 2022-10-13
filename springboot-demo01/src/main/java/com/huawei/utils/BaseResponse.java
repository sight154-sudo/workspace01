package com.huawei.utils;

import cn.hutool.http.HttpStatus;
import lombok.Data;

/**
 * @author king
 * @date 2022/7/22-23:33
 * @Desc
 */
@Data
public class BaseResponse<T> {
    private int code;
    private String message;
    private T obj;

    public BaseResponse() {
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, T obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    public static BaseResponse ok() {
        return new BaseResponse(HttpStatus.HTTP_OK, "operation success");
    }

    public static BaseResponse failed() {
        return new BaseResponse(HttpStatus.HTTP_INTERNAL_ERROR, " operation failed");
    }


}
