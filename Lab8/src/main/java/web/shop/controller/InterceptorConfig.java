package web.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    LogInterceptor logInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/account/**", "/order/**", "/admin/**");


        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/account/change-password", "/account/edit-profile", "/order/**")
                .excludePathPatterns("/admin/home/index");
    }
}
