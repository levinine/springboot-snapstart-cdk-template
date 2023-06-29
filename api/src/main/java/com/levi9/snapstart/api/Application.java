package com.levi9.snapstart.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.levi9.snapstart")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}