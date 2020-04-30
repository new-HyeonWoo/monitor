package com.ohap.monitor.config.auth.dto.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GenerateCode {
    String value();
    String type() default "type";
    String name() default "name";
    int order() default 0;
}
