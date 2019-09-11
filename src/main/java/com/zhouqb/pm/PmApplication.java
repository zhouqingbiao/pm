package com.zhouqb.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class PmApplication {
    public static void main(String[] args) {
        SpringApplication.run(PmApplication.class, args);
    }
}
