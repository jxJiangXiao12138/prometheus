package com.demo.spring.prometheus;

import io.prometheus.client.Summary;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrometheusMonitorAspect {


    @Pointcut("@annotation(com.demo.spring.prometheus.PrometheusMethodMonitor)")
    public void point() {
    }

    @Around(value = "point() && @annotation(annotation)")
    public Object MetricsCollector(ProceedingJoinPoint joinPoint, PrometheusMethodMonitor annotation) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        if(annotation != null) {
            String name;
            if(StringUtils.isNotEmpty(annotation.name())) {
                name = annotation.name();
            } else {
                name = className+"."+methodName;
            }
            PrometheusType.requestTotal.labels(name,methodName).inc();
            Summary.Timer requestTimer = PrometheusType.responseSummary.labels(name,methodName).startTimer();
            Object object;
            try {
                object = joinPoint.proceed();
            } catch (Exception e) {
                PrometheusType.requestError.labels(name,methodName).inc();
                throw new RuntimeException(e.getMessage());
            } finally {
                requestTimer.observeDuration();
            }
            return object;
        } else {
            return joinPoint.proceed();
        }
    }

}