package com.zhouqb.pm.advice;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 非200错误拦截
 */
@ControllerAdvice
public class ErrorControllerAdvice implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        // 返回错误信息
        modelAndView.addObject("status", status);

        modelAndView.setViewName("Status");

        return modelAndView;
    }
}