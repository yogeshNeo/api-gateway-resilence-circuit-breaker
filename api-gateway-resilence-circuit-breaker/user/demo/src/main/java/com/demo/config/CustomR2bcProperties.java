package com.demo.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@ConstructorBinding
@ConfigurationProperties("spring.r2dbc")
@AllArgsConstructor
@Component
@NoArgsConstructor
public class CustomR2bcProperties {

    private String url;
    private String username;
    private String password;
    private String host;
    private String port;
    private Map<String, String> database;

}