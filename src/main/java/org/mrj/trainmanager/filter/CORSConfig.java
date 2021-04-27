package org.mrj.trainmanager.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer{
    //跨域配置类
    @Override
    public void addCorsMappings(CorsRegistry registry) {//会给http请求进行加工从而解决跨域
        registry.addMapping("/**").allowedOriginPatterns("*")//把所有页面/方法加到映射器里，以至于对其进行跨域配置
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")//允许的方法种类
                .allowCredentials(true).maxAge(3600);//是否允许携带cookie
    }
}
