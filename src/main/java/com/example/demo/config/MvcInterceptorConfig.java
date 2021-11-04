package com.example.demo.config;

import com.example.demo.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: Mr.monster.liu
 * @create: 2021-11-04 16:28
 **/
@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    public LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor拦截器
        registry.addInterceptor(loginHandlerInterceptor)
                //拦截路径
                .addPathPatterns("/**")
                //不拦截路径
                .excludePathPatterns("/error",
                        "/**/**/**/*.{js,html}",
                        "/i18n/**",
                        "/api/provost/**",
                        "/content/**",
                        "/swagger-ui/index.html",
                        "/api/user/login",
                        "/soft/download.html",
                        "/api/user/login/loginByCode",
                        "/api/user/login/bindPhone",
                        "/**/doc.html",
                        "/webjars/bycdao-ui/images/api.ico",
                        "/swagger-resources");
    }



}
