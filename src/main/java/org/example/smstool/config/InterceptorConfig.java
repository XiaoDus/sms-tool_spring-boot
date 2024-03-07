package org.example.smstool.config;

import org.example.smstool.common.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**") // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/userinfo/getSessionKey")
                .excludePathPatterns("/files/upload")
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v3/**",
                        "/swagger-ui.html/**");

    }
    @Bean
    public HandlerInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
