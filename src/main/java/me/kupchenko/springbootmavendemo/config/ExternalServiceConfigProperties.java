package me.kupchenko.springbootmavendemo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "external.service")
public class ExternalServiceConfigProperties {
    /**
     * You can also use any of the supported units. These are:
     * • ns for nanoseconds
     * • us for microseconds
     * • ms for milliseconds
     * • s for seconds
     * • m for minutes
     * • h for hours
     * • d for days
     * <p>
     * The default unit is milliseconds and can be overridden using @DurationUnit
     */
    @DurationUnit(ChronoUnit.MILLIS)
    private Duration readTimeout;

    /**
     * You can also use any of the supported units. These are:
     * • B for bytes
     * • KB for kilobytes
     * • MB for megabytes
     * • GB for gigabytes
     * • TB for terabytes
     * <p>
     * The default unit is bytes and can be overridden using @DataSizeUnit
     */
    @DataSizeUnit(DataUnit.BYTES)
    private DataSize maxPayloadSize;

    private String baseUrl;

    @NotNull
    @Min(value = 1, message = "retry-attempts must be higher or equal to 1")
    private Integer retryAttempts;

    private List<String> tlsVersions;


}
