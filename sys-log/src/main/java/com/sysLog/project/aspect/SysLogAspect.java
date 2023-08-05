package com.sysLog.project.aspect;

import com.sysLog.project.annotation.SysLogAnnotation;
import com.sysLog.project.common.BaseResponse;
import com.sysLog.project.common.ResponseCodeEnum;
import com.sysLog.project.mapper.SysLogMapper;
import com.sysLog.project.model.entity.SysLog;
import com.sysLog.project.util.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.Timestamp;

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;


    // 设置切点
    @Pointcut("@annotation(com.sysLog.project.annotation.SysLogAnnotation)")
    private void pointCut() {
    }


    // 设置接口开始时间和消耗时间
    private Object setSpendTime(ProceedingJoinPoint pjp, SysLog sysLog) throws Throwable {

        // 记录接口访问时间
        long startTime = System.currentTimeMillis();

        // 调用目标接口
        Object result = pjp.proceed(pjp.getArgs());

        // 计算消耗时间 (s)
        long spendTime = (System.currentTimeMillis() - startTime) / 1000;

        sysLog.setOperateTime(new Timestamp(startTime)); // 设置开始时间
        sysLog.setSpendTime(spendTime); // 设置消耗时间

        // 返回结果
        return result;
    }

    // 设置响应状态码
    private void setResponseCode(SysLog sysLog, Object result) {
        // 判断返回体类型是否为BaseResponse
        if (result instanceof BaseResponse) {
            sysLog.setResponseCode(((BaseResponse<?>) result).getCode());
        }
    }


    // 设置请求体
    private void setRequestBody(HttpServletRequest request, SysLog sysLog) throws IOException {
        // ContentCachingRequestWrapper这个类读取请求体
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        sysLog.setBody(requestBody);

    }

    // 设置ip地址
    private void setIpAddress(HttpServletRequest request, SysLog sysLog) throws Exception {
        if (request != null) {
            try {
                sysLog.setIp(IPUtils.getIpAddr(request));
            } catch (Exception e) {
                sysLog.setIp(null);
            }
        }

    }

    // 设置操作类型
    private void setLogType(ProceedingJoinPoint pjp, SysLog sysLog) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        // 获取切入点所在的方法
        Method method = signature.getMethod();

        SysLogAnnotation annotation = method.getAnnotation(SysLogAnnotation.class);

        sysLog.setLogType(annotation.logType().getType());

    }

    // 创建操作日志
    private SysLog createOperateLog(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        SysLog sysLog = new SysLog();
        sysLog.setQuery(request.getQueryString()); // 设置query参数

        this.setRequestBody(request, sysLog); // 设置请求体
        this.setIpAddress(request, sysLog); // 设置ip地址
        this.setLogType(pjp, sysLog); // 设置操作类型
        sysLog.setUrl(request.getServletPath()); // 设置请求url


        return sysLog;
    }

    //    @Around("execution(public * com.sysLog.project.controller.*.*(..))") // 针对所有controller下面所有方法
    @Around("pointCut()") // 只针对切点的才会执行
    public Object postLogAspect(ProceedingJoinPoint pjp) throws Throwable {

        SysLog sysLog = this.createOperateLog(pjp); // 创建一个操作日志
        Object result = this.setSpendTime(pjp, sysLog); // 设置消耗时间
        this.setResponseCode(sysLog, result);

        if (sysLog.getResponseCode() != ResponseCodeEnum.RC200.getCode()) {
            sysLog.setException(((BaseResponse<?>) result).getMsg());
        }

        sysLogMapper.insert(sysLog);

        return result;
    }

}
