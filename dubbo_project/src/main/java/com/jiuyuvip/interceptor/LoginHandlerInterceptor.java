package com.jiuyuvip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuyuvip.util.Tools;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.*;


/**
 *
 * 类名称：LoginHandlerInterceptor.java
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String path = request.getServletPath();
        String path = request.getRequestURL().toString();
        request.setAttribute("path",path);
        JSONObject jsonObject = JSONObject.fromObject(request.getParameterMap());
        request.setAttribute("request_map", jsonObject.toString());
        request.setAttribute("begin_nao_time", System.nanoTime());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception arg3)  throws Exception {

        long begin_nao_time = (Long) req.getAttribute("begin_nao_time");
        String path = (String) req.getAttribute("path");
        //获取参数
//        HandlerMethod h=(HandlerMethod)handler;
//        String result= h.getMethod().toString();
//        //
        String request_map = (String) req.getAttribute("request_map");
        long interval = System.nanoTime() - begin_nao_time;//调用时间
//        System.out.println("接口"+path+":调用时间为（毫微秒）"+interval);
        LoggerContent logger=new LoggerContent(String.valueOf((new Date()).getTime()+Tools.getRandomNum()));
        logger.setPath(path);
        logger.setLongtime(String.valueOf(interval/1000000));
        logger.setRequestmap(request_map);
        logger.setResult("1");
//         ServiceHelper.getMongodbLogger().insert(logger);
     }

}