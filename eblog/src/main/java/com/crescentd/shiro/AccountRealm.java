package com.crescentd.shiro;

import com.crescentd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dyh
 * @description:
 * @date 2020/4/28
 **/
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        AccountProfile profile = userService.login(token.getUsername(), String.valueOf(token.getPassword()));
        log.info("登录认证开始");
        SecurityUtils.getSubject().getSession().setAttribute("profile",profile);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());

        return simpleAuthenticationInfo;
    }
}
