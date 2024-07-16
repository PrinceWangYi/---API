package com.prince.blog_system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.prince.blog_system.domain.ResultVo;
import com.prince.blog_system.domain.User;
import com.prince.blog_system.domain.dto.UserDto;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.mapper.UserMapper;
import com.prince.blog_system.service.UserService;
import com.prince.blog_system.util.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.prince.blog_system.constant.UserConstant.LOGIN_USER_KEY;
import static com.prince.blog_system.constant.UserConstant.TOKEN_KEY;


@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register/{username}/{password}/{email}")
    public ResultVo register(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("email") String email) {
        int insert = userService.register(username, password, email);
        ThrowUtil.throwIf(insert <= 0, BlogExceptionEnum.INSERT_FAILED);
        return ResultVo.success();
    }

    @GetMapping("/login/{username}/{password}")
    public ResultVo login(@PathVariable("username") String username, @PathVariable("password") String password, HttpSession session) {
        UserDto loginDto = userService.login(username, password, session);
        return ResultVo.success(loginDto);
    }

    @GetMapping("/getCurrentUser")
    public ResultVo getCurrentUser(HttpSession session) {
        User currentUser = (User) session.getAttribute(LOGIN_USER_KEY);
        ThrowUtil.throwIf(currentUser == null, BlogExceptionEnum.VALID_LOGIN_USER);
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(currentUser, userDto);
        return ResultVo.success(userDto);
    }

    @GetMapping("/logout")
    public ResultVo logout(HttpSession session) {
        session.removeAttribute(LOGIN_USER_KEY);
        session.removeAttribute(TOKEN_KEY);
        return ResultVo.success();
    }

}
