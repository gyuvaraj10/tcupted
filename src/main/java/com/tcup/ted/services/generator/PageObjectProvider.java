package com.tcup.ted.services.generator;

import com.tcup.ted.services.generator.impl.AppiumPOGenerator;
import com.tcup.ted.services.generator.impl.SeleniumPOGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class PageObjectProvider implements FactoryBean<IPageObjectGenerator>, ApplicationContextAware {

    @Value("${automation.tool.name}")
    private String toolName;

    private ApplicationContext applicationContext;

    private static Logger log4JLogger = LoggerFactory.getLogger(PageObjectProvider.class);

    @Override
    public IPageObjectGenerator getObject() {
        log4JLogger.info("Requested the PageObject For {} Tool", toolName);
        IPageObjectGenerator pageObjectGenerator;
        switch (toolName.toLowerCase()) {
            case "selenium" : {
                pageObjectGenerator = applicationContext.getBean(SeleniumPOGenerator.class);
                break;
            }
            case "appium" : {
                pageObjectGenerator =  applicationContext.getBean(AppiumPOGenerator.class);
                break;
            }
            default: {
                pageObjectGenerator =  applicationContext.getBean(SeleniumPOGenerator.class);
                break;
            }
        }
        return pageObjectGenerator;
    }

    @Override
    public Class<IPageObjectGenerator> getObjectType() {
        return IPageObjectGenerator.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
