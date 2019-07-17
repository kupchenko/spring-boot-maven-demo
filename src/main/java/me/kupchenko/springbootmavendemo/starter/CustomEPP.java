package me.kupchenko.springbootmavendemo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

public class CustomEPP implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("Current active profiles: " + Arrays.toString(environment.getActiveProfiles()));
        if (environment.getActiveProfiles().length == 0) {
            String os = System.getenv().get("OS");
            if (os == null || os.contains("Windows")) {
                environment.addActiveProfile("DEV");
            }
        }
    }
}
