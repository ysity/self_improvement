package com.crescentd.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
public class BaseController {
    @Autowired
    HttpServletRequest request;
}
