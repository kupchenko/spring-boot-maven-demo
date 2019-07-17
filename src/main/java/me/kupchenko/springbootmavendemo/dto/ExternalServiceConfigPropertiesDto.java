package me.kupchenko.springbootmavendemo.dto;

import lombok.Data;
import me.kupchenko.springbootmavendemo.config.ExternalServiceConfigProperties;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExternalServiceConfigPropertiesDto {

    private String readTimeout;
    private String maxPayloadSize;
    private String baseUrl;
    private String retryAttempts;
    private List<String> tlsVersions;

    public ExternalServiceConfigPropertiesDto(ExternalServiceConfigProperties properties) {
        this.readTimeout = properties.getReadTimeout().toString();
        this.maxPayloadSize = properties.getMaxPayloadSize().toString();
        this.baseUrl = properties.getBaseUrl();
        this.retryAttempts = properties.getRetryAttempts().toString();
        this.tlsVersions = new ArrayList<>(properties.getTlsVersions());
    }
}
