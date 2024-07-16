package com.prince.blog_system.util;

import com.prince.blog_system.exception.BlogException;
import com.prince.blog_system.exception.BlogExceptionEnum;

public class ThrowUtil {

    public static void throwIf (boolean flag, BlogExceptionEnum blogExceptionEnum) {
        if (flag) throw new BlogException(blogExceptionEnum.getCode(), blogExceptionEnum.getMsg());
    }
}
