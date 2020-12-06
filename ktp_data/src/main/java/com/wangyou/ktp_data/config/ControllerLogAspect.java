package com.wangyou.ktp_data.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class ControllerLogAspect {
    private final static Logger controllerLogger = LoggerFactory.getLogger(ControllerLogAspect.class);

    /**
     * 定义切面
     */
    @Pointcut("execution(* com.wangyou.ktp_data.controller.*.*(..))")
    public void controllerLog() {
    }

    @Before(value = "controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        System.out.println(name);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String methodType = request.getMethod();

        //打印日志
        controllerLogger.debug("-----------------------------------------------------------------------------------------------------");
        controllerLogger.debug("时间 = {}", format);
        controllerLogger.debug("访问url = {}", url);
        controllerLogger.debug("请求方法类型 = {}", methodType);
        controllerLogger.debug("来源ip地址 = {}", ip);
        controllerLogger.debug("调用方法 = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @Around(value = "controllerLog()")
    public Object doAfterController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        controllerLogger.debug("执行时间 = {}", time + "ms");
        controllerLogger.debug("-----------------------------------------------------------------------------------------------------");
        return object;
    }

}
