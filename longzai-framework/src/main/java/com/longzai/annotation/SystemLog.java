package com.longzai.annotation;

import org.aspectj.lang.annotation.Around;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Argument;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SystemLog {
    String businessName();
}
