package me.kupchenko.springbootmavendemo.starter;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class CustomContextInit implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("Checking for profiles...");
        if (configurableApplicationContext.getEnvironment().getActiveProfiles().length == 0) {
            throw new IllegalStateException("No profiles found!!!!");
        }
    }
}