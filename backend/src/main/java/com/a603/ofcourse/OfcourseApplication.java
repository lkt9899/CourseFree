package com.a603.ofcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OfcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfcourseApplication.class, args);
    }

}
