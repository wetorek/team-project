package com.wetorek.teamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class TeamProjectApplication{

    public static void main(String[] args) {
        SpringApplication.run(TeamProjectApplication.class, args);
    }

}
