package me.kupchenko.springbootmavendemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Slf4j
@Configuration
@EnableConfigurationProperties({ExternalServiceConfigProperties.class})
public class AppConfig {

    @Value("${external.service.base-url}")
    private String baseUrl;

    @Bean
    @Scope("prototype")
    public RestTemplate getRestClient() {
        log.info("Configuring RestTemplate with base url: {}", baseUrl);
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        return restTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(name = "contextEventListener")
    public OneTestBean oneTestBean() {
        return new OneTestBean();
    }

    public static class OneTestBean {
    }
}