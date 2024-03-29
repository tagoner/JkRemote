package com.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpAllowInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//컨트롤러 진입 전
		log.info("PreHanlder : {}", request);
		
        //Controller 호출
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//컨트롤러 진입 후
		log.info("- PostHanlder -");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}