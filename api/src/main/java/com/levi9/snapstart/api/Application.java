package com.levi9.snapstart.api;

import com.levi9.snapstart.api.controller.DummyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({DummyController.class})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}