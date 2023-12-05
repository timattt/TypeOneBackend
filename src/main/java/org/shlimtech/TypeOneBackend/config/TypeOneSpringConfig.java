package org.shlimtech.TypeOneBackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeOneSpringConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
