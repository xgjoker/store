package com.sy.store.config;

import com.sy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/bootstrap3/**");
        whiteList.add("/css/**");
        whiteList.add("/images/**");
        whiteList.add("/js/**");
        whiteList.add("/web/register.html");
        whiteList.add("/web/login.html");
        whiteList.add("/web/index.html");
        whiteList.add("/web/product.html");
        whiteList.add("/users/reg");
        whiteList.add("/users/login");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(whiteList);
    }
}
