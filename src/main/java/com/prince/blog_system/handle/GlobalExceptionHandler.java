package com.prince.blog_system.handle;

import com.prince.blog_system.domain.Log;
import com.prince.blog_system.domain.ResultVo;
import com.prince.blog_system.exception.BlogException;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.mapper.LogMapper;
import com.prince.blog_system.util.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private LogMapper logMapper;

    @ExceptionHandler(BlogException.class)
    public ResultVo handle(BlogException e) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String requestURI = requestAttributes.getRequest().getRequestURI();
        Log logInfo = new Log();
        if (requestURI.length() > 64) {
            requestURI = requestURI.substring(0, 64);
        }
        logInfo.setRequestUrl(requestURI);
        logInfo.setExceptionCode(e.getCode());
        logInfo.setExceptionMsg(e.getMsg());
//        /api/createArticle/%E6%B5%8B%E8%AF%951/%E6%B5%8B%E8%AF%95%E5%86%85%E5%AE%B91

        int insert = logMapper.insert(logInfo);
        log.error("异常信息：{}", logInfo);
        ThrowUtil.throwIf(insert <= 0, BlogExceptionEnum.INSERT_FAILED);
        return ResultVo.error(e.getCode(), e.getMsg());
    }

}
