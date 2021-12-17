package com.streamdemo.config;

import com.streamdemo.converter.CustomMappingJackson2HttpMessageConverter;
import com.streamdemo.messaging.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class MessagingConfiguration {

    @Bean
  public MessageConverter anemoneMessageConverter(CustomMappingJackson2HttpMessageConverter converter) {
    return new StreamMessageConverter(converter.getObjectMapper());
  }

}
