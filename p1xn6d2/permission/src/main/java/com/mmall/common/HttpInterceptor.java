package com.mmall.common;

import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName : HttpInterceptor
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-13 16:35
 * @Description : Http 请求前后监听工具
 *
 *  http拦截器，请求时间计算
 */

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    private static final String START_TIME = "requestStartTime";

    /**
     * 请求准备实现，处理之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map  parameterMap = request.getParameterMap();
        logger.info("request start. url:{},params:{}",url, JsonMapper.obj2String(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        return true;
    }

    /**
     * 正常结束
     * 请求正常结束,处理完之后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
     /*   String url = request.getRequestURI().toString();
        *//*Map parameterMap = request.getParameterMap();
        logger.info("request finished. url:{},params:{}",url, JsonMapper.obj2String(parameterMap));*//*
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info("request finished. url:{},cost:{}",url,end -start);*/

       // removeThreadLocalInfo();
    }

    /**
     *
     * 请求结束之后，任何情况都调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  {
        String url = request.getRequestURI().toString();
        /* Map  parameterMap = request.getParameterMap();
        logger.info("request completed. url:{},params:{}",url, JsonMapper.obj2String(parameterMap));*/

        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        logger.info("request completed. url:{},cost:{}",url,end -start);

       // removeThreadLocalInfo();
    }

    /**
     * 移除变量信息
     * 如果不移除，那么变量不会释放掉，会造成内存泄漏
     * 在接口处理完以后进行处理（interceptor）
     */
    /*public void removeThreadLocalInfo() {
        RequestHolder.remove();
    }*/
}
