package com.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * annotation用到的config配置
 */
@Configuration
@ComponentScan("com.annotations.*")
public class ConfigAnnotation {
}
