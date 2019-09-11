package com.zhouqb.pm.advice;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ErrorControllerAdvice implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("Status", status);

        modelAndView.setViewName("Status");

        return modelAndView;

    }
}