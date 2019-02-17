package com.supersong.graduation.aop;


import com.google.gson.Gson;
import com.supersong.graduation.bean.WebLog;
import com.supersong.graduation.service.WebLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WebLogAop {

    @Autowired
    private WebLogService webLogService;

    @Pointcut("execution(public * com.supersong.graduation.controller..*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        WebLog webLog = new WebLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        webLog.setWebUrl(request.getRequestURL().toString());
        webLog.setIpAddress(request.getRemoteAddr());
        webLog.setHttpMethod(request.getMethod());
        webLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Gson gson = new Gson();
        webLog.setInputValue(gson.toJson(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        webLog.setOutputValue(gson.toJson(result));
        webLogService.add(webLog);
        return result;
    }
}
