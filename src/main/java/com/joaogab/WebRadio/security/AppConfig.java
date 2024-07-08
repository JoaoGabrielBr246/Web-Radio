package com.joaogab.WebRadio.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<IPFilter> ipFilter() {
        FilterRegistrationBean<IPFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IPFilter());
        registrationBean.addUrlPatterns("/admin");
        return registrationBean;
    }
}
