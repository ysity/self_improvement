package com.crescentd.service;

import com.crescentd.common.lang.Result;
import com.crescentd.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crescentd.shiro.AccountProfile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author crescentd
 * @since 2020-04-27
 */
public interface UserService extends IService<User> {

    Result register(User user);

    AccountProfile login(String username, String password);
}
