package com.songj.demo.config;

import com.songj.demo.model.UserValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * 定义全局校验
 */
//@Configuration
public class ValidatorConfig implements WebFluxConfigurer {
    /**
     * 静态资源访问设置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/static/**")// 可以通过url直接访问static下的任意指定的资源文件
                .addResourceLocations("classpath:/static/")// 指定classpath文件夹对外公开，也就是被spring上下文认知
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));// 设置缓存时间
    }

    /**
     * 设置全局校验器
     * @return
     */
    @Override
    public Validator getValidator() {
        return new UserValidator();
    }


}
