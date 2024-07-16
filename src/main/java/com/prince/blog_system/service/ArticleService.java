package com.prince.blog_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.prince.blog_system.domain.Article;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @author LENOVO
* @description 针对表【article(文章表)】的数据库操作Service
*/
public interface ArticleService extends IService<Article> {

    int createArticle(String title, String content, HttpSession session);

    int updateArticle(int postId, String title, String content, HttpSession session);

    int deleteById(Integer postId, HttpSession session);

    List<Article> limitQuery(int currentPage, int pageSize, boolean isOrder);
}
