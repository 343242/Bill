package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
	
	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/static/**")
            .excludePathPatterns("/front/**")
            .excludePathPatterns("/admin/**");
        super.addInterceptors(registry);
	}
	
	/**
	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/front/**")
            .addResourceLocations("classpath:/front/");
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/", "file:static/");
        registry.addResourceHandler("/upload/**")
            .addResourceLocations("classpath:/static/upload/", "file:static/upload/");
        registry.addResourceHandler("/admin/**")
            .addResourceLocations("classpath:/admin/admin/");
        registry.addResourceHandler("/resources/**")
            .addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/public/**")
            .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }
}
