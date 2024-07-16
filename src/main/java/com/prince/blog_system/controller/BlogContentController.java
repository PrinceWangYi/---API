package com.prince.blog_system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.prince.blog_system.domain.Article;
import com.prince.blog_system.domain.ResultVo;
import com.prince.blog_system.domain.User;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.mapper.ArticleMapper;
import com.prince.blog_system.service.ArticleService;
import com.prince.blog_system.util.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
public class BlogContentController {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleService articleService;

    @PostMapping("createArticle/{title}/{content}")
    public ResultVo createArticle(@PathVariable("title") String title, @PathVariable("content") String content, HttpSession session) {
        int insert = articleService.createArticle(title, content, session);
        ThrowUtil.throwIf(insert <= 0, BlogExceptionEnum.INSERT_FAILED);
        return ResultVo.success();
    }

    @PostMapping("getArticle/{postId}")
    public ResultVo getArticle(@PathVariable("postId") Integer postId) {
        Article article = articleMapper.selectById(postId);
        ThrowUtil.throwIf(article == null, BlogExceptionEnum.NULL_ART_LIST);
        return ResultVo.success(article);
    }

    @GetMapping("getArticleList/{currentPage}/{pageSize}/{isOrder}")
    public ResultVo getArticleList(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @PathVariable("isOrder") boolean isOrder) {
        List<Article> page = articleService.limitQuery(currentPage, pageSize, isOrder);
        return ResultVo.success(page);
    }

    @GetMapping("updateArticle/{postId}/{title}/{content}")
    public ResultVo updateArticle(@PathVariable("postId") int postId, @PathVariable("title") String title, @PathVariable("content") String content, HttpSession session) {
        int update = articleService.updateArticle(postId, title, content, session);
        ThrowUtil.throwIf(update <= 0, BlogExceptionEnum.UPDATE_INVALID);
        return ResultVo.success();
    }

    @PostMapping("deleteArticle/{postId}")
    public ResultVo deleteArticle(@PathVariable("postId") Integer postId, HttpSession session) {
        int delete = articleService.deleteById(postId, session);
        ThrowUtil.throwIf(delete <= 0, BlogExceptionEnum.DELETE_INVALID);
        return ResultVo.success();
    }

}
