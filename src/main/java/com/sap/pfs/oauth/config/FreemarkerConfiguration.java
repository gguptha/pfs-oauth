package com.sap.pfs.oauth.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FreemarkerConfiguration implements BeanPostProcessor {

    @Value("${oauth.signupUrl}")
    private String myProp;

    @Value("${oauth.forgotPassword}")
    private String myProp1;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof FreeMarkerConfigurer) {
            FreeMarkerConfigurer configurer = (FreeMarkerConfigurer) bean;
            Map<String, Object> sharedVariables = new HashMap<>();
            sharedVariables.put("signupUrl", myProp);
            sharedVariables.put("forgotPassword", myProp1);
            configurer.setFreemarkerVariables(sharedVariables);
        }
        return bean;
    }
}