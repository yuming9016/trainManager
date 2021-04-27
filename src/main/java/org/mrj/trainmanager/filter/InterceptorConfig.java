package org.mrj.trainmanager.filter;

import org.mrj.trainmanager.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //这个类会根据设置拦截页面/方法
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//这个是拦截器注册器
        registry.addInterceptor(jwtInterceptor)//传拦截器实体
                .excludePathPatterns("/addUser")//放行注册接口
                .excludePathPatterns("/login")//放行login接口

                .addPathPatterns("/**");

    }

}
