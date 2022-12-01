package com.indocyber.jasindo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/home/index");
        registry.addViewController("/doctor").setViewName("forward:/doctor/index");
        registry.addViewController("/patient").setViewName("forward:/patient/index");
        registry.addViewController("/reservation").setViewName("forward:/reservation/index");
    }
}
