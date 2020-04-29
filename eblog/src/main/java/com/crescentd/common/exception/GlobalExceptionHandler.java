package com.crescentd.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dyh
 * @description:
 * @date 2020/4/27
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e){
        log.info("------------------>捕捉到全局异常", e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("Exception",e);
        modelAndView.addObject("message",e.getMessage());
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
