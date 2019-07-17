package me.kupchenko.springbootmavendemo.controller;

import lombok.extern.slf4j.Slf4j;
import me.kupchenko.springbootmavendemo.dto.ExternalServiceConfigPropertiesDto;
import me.kupchenko.springbootmavendemo.config.ExternalServiceConfigProperties;
import me.kupchenko.springbootmavendemo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Slf4j
@Controller
@RequestMapping("/external/service")
public class ExternalServiceController {


    @Value("${external.service.base-url}")
    private String baseUrl;

    private RestTemplate restTemplate;
    private ExternalServiceConfigProperties externalServiceConfigProperties;

    public ExternalServiceController(ExternalServiceConfigProperties externalServiceConfigProperties, RestTemplate restTemplate) {
        this.externalServiceConfigProperties = externalServiceConfigProperties;
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
    }

    @GetMapping("/request")
    private ResponseEntity<User> getSomethingFromExternalService() {
        User responseObject = restTemplate.getForObject("/external/service", User.class);

        return ResponseEntity.ok().body(responseObject);
    }

    @GetMapping("/")
    private ResponseEntity<User> endpointForExternalService() {
        return ResponseEntity.ok().body(new User("Dmitrii", "Kupchenko"));
    }

    @GetMapping("/props")
    private ResponseEntity<ExternalServiceConfigPropertiesDto> getProps() {
        return ResponseEntity.ok().body(new ExternalServiceConfigPropertiesDto(externalServiceConfigProperties));
    }
}
