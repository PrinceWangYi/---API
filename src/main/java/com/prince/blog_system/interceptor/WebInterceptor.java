package com.prince.blog_system.interceptor;

import cn.hutool.core.util.StrUtil;
import com.prince.blog_system.exception.BlogException;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.util.TokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.prince.blog_system.constant.UserConstant.TOKEN_KEY;

@Slf4j
public class WebInterceptor implements HandlerInterceptor {

    //拦截url地址
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        List<String> excludePath = new ArrayList<>();
        excludePath.add("login");
        excludePath.add("register");
        excludePath.add("api-docs");
        for (String pattern : excludePath) {
            if (requestURI.contains(pattern)) {
                return true;
            }
        }

        String token = request.getSession().getAttribute(TOKEN_KEY) == null
                ? "" : request.getSession().getAttribute(TOKEN_KEY).toString();

        Claims claims = TokenUtil.validateToken(token);

        if (claims == null) {
            throw new BlogException(BlogExceptionEnum.TOKEN_INVALID.getCode(), BlogExceptionEnum.TOKEN_INVALID.getMsg());
            // return false;
        }
        log.info("Token is valid, 有效继续");

        // Token有效，可以继续处理请求
        return true;
    }
}
