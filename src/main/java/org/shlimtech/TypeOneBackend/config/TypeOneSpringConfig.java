package org.shlimtech.TypeOneBackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TypeOneSpringConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TO FIX CORS ERROR
        registry.addMapping("/**").allowedMethods("*");
    }
}
