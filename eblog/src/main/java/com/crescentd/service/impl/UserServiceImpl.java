package com.crescentd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crescentd.common.lang.Result;
import com.crescentd.entity.User;
import com.crescentd.mapper.UserMapper;
import com.crescentd.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crescentd.shiro.AccountProfile;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Result register(User user) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword())){
            return Result.fail("必要字段不能为空");
        }
        int count = this.count(new QueryWrapper<User>()
                .eq("email",user.getEmail())
                .or()
                .eq("username",user.getUsername()));
        if (count > 0){
            return Result.fail("用户已存在");
        }
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(SecureUtil.md5(user.getPassword()));
        user1.setEmail(user.getEmail());
        user1.setAvatar("/image/avatar/default.jpg");
        user1.setCreated(new Date());
        user1.setPoint(0);
        user1.setVipLevel(0);
        user1.setCommentCount(0);
        user1.setPostCount(0);
        user1.setGender("0");
        return this.save(user1) ? Result.succ() : Result.fail("注册失败",null);
    }

    @Override
    public AccountProfile login(String username, String password) {
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null){
            throw new UnknownAccountException();
        }
        if (!user.getPassword().equals(password)){
            throw new IncorrectCredentialsException();
        }

        user.setLasted(new Date());
        this.updateById(user);
        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(user,accountProfile);
        return accountProfile;
    }
}
