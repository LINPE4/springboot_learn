package com.wisely.highlight_springmvc4;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc// 1 EnableWebMvc开启Springmvc支持，若无此句，重写WebMvcConfigurerAdapter方法无效
@EnableScheduling
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2 集成WebMvcConfigurerAdapter，重写其方法可对spring mvc进行配置

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    //addResourceLocations指的是是文件放置的目录，addResourceHandler指的是对外暴露的路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations(
                "classpath:/assets/");// 3 addResourceLocations指的是文件放置的目录,addResourceHandler指的是对外暴露的访问路径

    }

    @Bean
    // 1 配置拦截器的Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {// 2 重写addInterceptors方法，注册拦截器
        registry.addInterceptor(demoInterceptor());
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) { //通过重写configurePathMatch方法可以不忽略"."后面的参数
        configurer.setUseSuffixPatternMatch(false);
    }


}
