package com.crescentd.config;

import com.crescentd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
@Component
public class ContextStartup implements ApplicationRunner,ServletContextAware{
    private ServletContext servletContext;

    @Autowired
    private PostService postService;

    //初始化首页的周评论排行榜
    @Override
    public void run(ApplicationArguments args) throws Exception {
        servletContext.setAttribute("base",servletContext.getContextPath());
        postService.initWeekRank();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
