package me.kupchenko.springbootmavendemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties({ExternalServiceConfigProperties.class})
public class AppConfig {

    @Bean
    @Scope("prototype")
    public RestTemplate getRestClient() {
        return new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    @Bean
    @ConditionalOnMissingBean(name = "contextEventListener")
    public OneTestBean oneTestBean() {
        return new OneTestBean();
    }

    public static class OneTestBean {
    }
}