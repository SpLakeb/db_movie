package com.cinema.config;

import com.cinema.interceptor.CorsInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域请求的路径
                .allowedOrigins("http://localhost:8080") // 前端应用的地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                .allowCredentials(true) // 允许发送Cookie
                .maxAge(3600); // 预检请求的缓存时间
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(100));
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        return factory.createMultipartConfig();
    }
}
