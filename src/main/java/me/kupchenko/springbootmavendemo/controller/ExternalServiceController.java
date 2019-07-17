package me.kupchenko.springbootmavendemo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kupchenko.springbootmavendemo.config.ExternalServiceConfigProperties;
import me.kupchenko.springbootmavendemo.dto.ExternalServiceConfigPropertiesDto;
import me.kupchenko.springbootmavendemo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/external/service")
public class ExternalServiceController {

    private RestTemplate restTemplate;
    private ExternalServiceConfigProperties externalServiceConfigProperties;

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
