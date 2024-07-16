package com.prince.blog_system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prince.blog_system.domain.User;
import com.prince.blog_system.domain.dto.UserDto;
import com.prince.blog_system.exception.BlogExceptionEnum;
import com.prince.blog_system.mapper.UserMapper;
import com.prince.blog_system.service.UserService;
import com.prince.blog_system.util.ThrowUtil;
import com.prince.blog_system.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.prince.blog_system.constant.UserConstant.LOGIN_USER_KEY;
import static com.prince.blog_system.constant.UserConstant.TOKEN_KEY;

/**
* @author LENOVO
* @description 针对表【user(用户表)】的数据库操作Service实现
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int register(String username, String password, String email) {
        User user = User.builder().username(username).password(password).email(email).build();
        String md5 = SecureUtil.md5(user.getPassword());
        user.setPassword(md5);
        return userMapper.insert(user);
    }

    @Override
    public UserDto login(String username, String password, HttpSession session) {
        User loginUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)
                .eq(User::getPassword, SecureUtil.md5(password)));
        ThrowUtil.throwIf(loginUser == null, BlogExceptionEnum.USER_LOGIN_FAILED);
        String generateToken = TokenUtil.generateToken(loginUser);
        session.setAttribute(LOGIN_USER_KEY, loginUser);
        session.setAttribute(TOKEN_KEY, generateToken);
        UserDto userDto = new UserDto();
        BeanUtil.copyProperties(loginUser, userDto);
        return userDto;
    }
}




