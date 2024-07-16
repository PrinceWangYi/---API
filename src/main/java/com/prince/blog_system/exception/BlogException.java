package com.prince.blog_system.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogException extends RuntimeException{

    private String msg;
    private String code;

    public BlogException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
