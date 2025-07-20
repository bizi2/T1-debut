package com.weyland.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WeylandWatchingYou {
    boolean logToConsole() default true;
    String kafkaTopic() default "";
}