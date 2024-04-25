package com.happy3w.hire.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.happy3w.hire")
@MapperScan(basePackages = "com.happy3w.hire.mapper")
public class HireAutoConfiguration {
}
