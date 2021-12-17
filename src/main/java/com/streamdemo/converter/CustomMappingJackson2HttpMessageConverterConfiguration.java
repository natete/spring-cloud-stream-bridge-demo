package com.streamdemo.converter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class CustomMappingJackson2HttpMessageConverterConfiguration {

  private boolean autoDetectProperties = false;
}
