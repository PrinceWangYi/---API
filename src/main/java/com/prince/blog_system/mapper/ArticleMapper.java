package com.prince.blog_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.prince.blog_system.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author LENOVO
* @description 针对表【article(文章表)】的数据库操作Mapper
* @Entity com.prince.blog.domain.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from article LIMIT #{currentPage},#{pageSize}")
    List<Article> limitQuery(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);

}




