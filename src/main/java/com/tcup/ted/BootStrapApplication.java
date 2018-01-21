package com.tcup.ted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = {"com.tcup.ted"})
public class BootStrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}
