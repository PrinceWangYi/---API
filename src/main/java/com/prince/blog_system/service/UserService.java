package com.prince.blog_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prince.blog_system.domain.User;
import com.prince.blog_system.domain.dto.UserDto;

import javax.servlet.http.HttpSession;

/**
* @author LENOVO
* @description 针对表【user(用户表)】的数据库操作Service
*/
public interface UserService extends IService<User> {

    int register(String username, String password, String email);

    UserDto login(String username, String password, HttpSession session);
}
