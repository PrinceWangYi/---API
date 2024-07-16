# 博客系统开发文档

## 1.用户模块

### 	1.1用户登录

请求示例：/api/auth/login/{username}/{password}

### 	1.2用户注册

请求示例：/api/auth/register/{username}/{password}/{email}

### 	1.3获取当前登录用户

请求示例：/api/auth/getCurrentUser

## 2.博客内容管理模块
### 	2.1创建文章

请求示例：/api/posts/createArticle/{title}/{content}

### 	2.2更新文章

请求示例：/api/posts/updateArticle/{postId}/{title}/{content}

### 	2.3删除文章

请求示例：/api/posts/deleteArticle/{postId}

### 	2.4获取单篇文章

请求示例：/api/posts/getArticle/{postId}

### 	2.5分页获取文章列表

请求示例：/api/posts/getArticleList/{currentPage}/{pageSize}/{isOrder}

## 3.docker部署流程

​	将blog_system-0.0.1-SNAPSHOT.jar包和dockerfile文件上传至统一文件目录下，执行命令

```
docker build . -t blog-system:v1.0
```

​	镜像构建完成后执行

```
docker run -d -p 8081:8081 blog-system:v1.0
```

