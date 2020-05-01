package com.crescentd.controller;

import cn.hutool.crypto.SecureUtil;
import com.crescentd.common.lang.LoginRegEnum;
import com.crescentd.common.lang.Result;
import com.crescentd.entity.User;
import com.crescentd.entity.vo.RequestVo;
import com.crescentd.service.UserService;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
@Controller
public class IndexController extends BaseController {

    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }


    @RequestMapping("/auth/kaptcha.jpg")
    public void kaptcha(HttpServletResponse response) throws IOException {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(KAPTCHA_SESSION_KEY,text);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image,"jpg",outputStream);
    }

    @RequestMapping("/auth")
    public String login(){
        return "/auth/login";
    }

    @RequestMapping("/auth/doLogin")
    @ResponseBody
    public Result doLogin(String username, String password){
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return Result.fail("用户名或者密码不能为空");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, SecureUtil.md5(password));
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException){
                return Result.fail(LoginRegEnum.UnknownAccount.getMsg(),LoginRegEnum.UnknownAccount.getCode());
            }else if (e instanceof LockedAccountException){
                return Result.fail(LoginRegEnum.LockedAccount.getMsg(),LoginRegEnum.LockedAccount.getCode());
            }else if (e instanceof IncorrectCredentialsException){
                return Result.fail(LoginRegEnum.IncorrectCredentials.getMsg(),LoginRegEnum.IncorrectCredentials.getCode());
            }else {
                return Result.fail(LoginRegEnum.AuthFailed.getMsg(),LoginRegEnum.AuthFailed.getCode());
            }
        }
        Result result = new Result();
        result.setMsg("登录成功");
        result.setAction("/auth/register_login");
        return result;
    }


    @RequestMapping("/auth/reg")
    public String reg(){
        return "/auth/reg";
    }

    @RequestMapping("/auth/register")
    @ResponseBody
    public Result doRegister(@RequestBody RequestVo requestVo){
        String kaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(requestVo.getCaptcha())) {
            return Result.fail("验证码不正确");
        }
        String repassword = requestVo.getRepassword();
        User user = requestVo.getUser();
        if (repassword == null || !repassword.equals(user.getPassword())){
            return Result.fail("两次密码不一致");
        }
        Result result = userService.register(user);
        result.setAction("/auth/register_login");
        result.setMsg("注册成功！");
        return result;
    }


    @RequestMapping("/auth/register_login")
    public String register_done(){
        return "/auth/register_login";
    }

    @RequestMapping("/auth/logout")
    @ResponseBody
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ("退出成功");
    }
}
