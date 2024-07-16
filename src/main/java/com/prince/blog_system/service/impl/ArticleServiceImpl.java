package com.prince.blog_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prince.blog_system.aspect.AuthCheck;
import com.prince.blog_system.domain.Article;
import com.prince.blog_system.domain.User;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.mapper.ArticleMapper;
import com.prince.blog_system.mapper.UserMapper;
import com.prince.blog_system.service.ArticleService;
import com.prince.blog_system.util.ThrowUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.prince.blog_system.constant.UserConstant.LOGIN_USER_KEY;

/**
* @author LENOVO
* @description 针对表【article(文章表)】的数据库操作Service实现
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int createArticle(String title, String content, HttpSession session) {
        User currentUser = (User)session.getAttribute(LOGIN_USER_KEY);
        ThrowUtil.throwIf(currentUser == null, BlogExceptionEnum.TOKEN_EXCEPTION);
        Article article = Article.builder().title(title).content(content).userId(currentUser.getUserId()).build();
        return articleMapper.insert(article);
    }

    @Override
    @AuthCheck
    public int updateArticle(int postId, String title, String content, HttpSession session) {
        User user = (User) session.getAttribute(LOGIN_USER_KEY);
        Article article = Article.builder().title(title).content(content).postId(postId).build();
        return articleMapper.updateById(article);
    }

    @Override
    @AuthCheck
    public int deleteById(Integer postId, HttpSession session) {
        User user = (User) session.getAttribute(LOGIN_USER_KEY);
        Long count = articleMapper.selectCount(new LambdaQueryWrapper<Article>().eq(Article::getUserId, user.getUserId())
                .eq(Article::getPostId, postId));
        ThrowUtil.throwIf(count == 0, BlogExceptionEnum.ONLY_SELF_UPDATE);
        return articleMapper.deleteById(postId);
    }

    @Override
    public List<Article> limitQuery(int currentPage, int pageSize, boolean isOrder) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<Article>()
                    .orderBy(true, isOrder, Article::getCreated);
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        System.out.println(articlePage);
        return articlePage.getRecords();
    }
}




