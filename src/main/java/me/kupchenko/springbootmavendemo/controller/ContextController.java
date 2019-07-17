package me.kupchenko.springbootmavendemo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kupchenko.springbootmavendemo.model.BeanInfo;
import me.kupchenko.springbootmavendemo.model.ContextData;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/context")
@AllArgsConstructor
public class ContextController {

    private ApplicationContext applicationContext;
    private ConfigurableListableBeanFactory factory;

    @GetMapping("/info")
    public String showHomePage(Model model) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        List<BeanInfo> beanInfoList = Stream.of(beanDefinitionNames)
                .map(name -> new BeanInfo(name, applicationContext.getType(name).getCanonicalName(), applicationContext.isSingleton(name)))
                .collect(Collectors.toList());
        model.addAttribute("contextData", new ContextData(beanInfoList));
        log.info("Request `/context/info` is processed! Found {} beans.", beanInfoList.size());
        return "contextInfo";
    }

    @ResponseBody //Could not resolve view with name 'context/info1' in servlet with name 'dispatcherServlet'
    @GetMapping(value = "/info1", produces = "application/json")
    public ContextData showHomePage() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        List<BeanInfo> beanInfoList = Stream.of(beanDefinitionNames)
                .map(name -> new BeanInfo(name, factory.getBeanDefinition(name).getBeanClassName(), applicationContext.isSingleton(name)))
                .collect(Collectors.toList());
        log.info("Request `/context/info` is processed! Found {} beans.", beanInfoList.size());
        return new ContextData(beanInfoList);
    }
}
