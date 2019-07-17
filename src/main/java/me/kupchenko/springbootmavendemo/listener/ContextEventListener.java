package me.kupchenko.springbootmavendemo.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Component
@AllArgsConstructor
public class ContextEventListener implements ApplicationListener<ContextRefreshedEvent> {
    private ConfigurableListableBeanFactory factory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Stream.of(applicationContext.getBeanDefinitionNames())
                .forEach(name -> {
                    BeanDefinition beanDefinition = factory.getBeanDefinition(name);
                    System.out.printf("Bean[%s] with class [%s]\n", name, beanDefinition.getBeanClassName());
//                    log.info("Bean[{}] with class [{}]", name, beanDefinition.getBeanClassName());
                });


    }
}