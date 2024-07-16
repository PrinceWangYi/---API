package com.prince.blog_system.domain;

import com.prince.blog_system.exception.BlogExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    private boolean flag;
    private String msg;
    private String code;
    private T data;

    public static ResultVo success() {
        return new ResultVo(true, BlogExceptionEnum.SUCCESS.getMsg(), BlogExceptionEnum.SUCCESS.getCode(), null);
    }

    public static <E> ResultVo success(E data) {
        return new ResultVo(true, BlogExceptionEnum.SUCCESS.getMsg(), BlogExceptionEnum.SUCCESS.getCode(), data);
    }

    public static ResultVo error() {
        return new ResultVo(false, BlogExceptionEnum.ERROR.getMsg(), BlogExceptionEnum.ERROR.getCode(), null);
    }

    public static <E> ResultVo error(String msg, String code) {
        return new ResultVo(false, msg, code, null);
    }
}
