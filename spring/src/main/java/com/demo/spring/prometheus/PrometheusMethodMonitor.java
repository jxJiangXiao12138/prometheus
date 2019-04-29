package com.demo.spring.prometheus;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrometheusMethodMonitor {
    /**
     * 默认为空,className.methodName作为Metric name
     * 如果name有设置值,使用name作为Metric name
     *
     * @return
     */
    String name() default "";


}