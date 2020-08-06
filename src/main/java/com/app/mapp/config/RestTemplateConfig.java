package com.app.mapp.config;

import com.app.mapp.error.RestTemplateResponseErrorHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {


  @Bean
  public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
    return new CustomRestTemplateCustomizer();
  }

  @Bean
  @DependsOn(value = {"customRestTemplateCustomizer"})
  public RestTemplateBuilder restTemplateBuilder() {
    return new RestTemplateBuilder(customRestTemplateCustomizer());
  }

  @Bean
  public List<HttpMessageConverter<?>> messageConverter() {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
    messageConverters.add(converter);
    return messageConverters;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder
        .errorHandler(new RestTemplateResponseErrorHandler())
        .messageConverters(byteArrayHttpMessageConverter())
        .build();
  }

  @Bean
  public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
    return new ByteArrayHttpMessageConverter();
  }

}