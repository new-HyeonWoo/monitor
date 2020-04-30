package com.ohap.monitor.config.auth.dto.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MobileRedirect {
    String prefix() default "/mobile";
    boolean isPath() default false;
    String path() default "";
}
