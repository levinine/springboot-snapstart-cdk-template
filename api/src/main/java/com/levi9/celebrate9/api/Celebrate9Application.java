package com.levi9.celebrate9.api;


import com.levi9.celebrate9.api.controller.DummyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({DummyController.class})
public class Celebrate9Application {

    public static void main(final String[] args) {
        SpringApplication.run(Celebrate9Application.class, args);
    }

}