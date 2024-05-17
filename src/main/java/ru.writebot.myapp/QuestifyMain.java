package ru.writebot.myapp;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuestifyMain {
    public static void main(String[] args) {
        SpringApplication.run(QuestifyMain.class);
        System.out.println("Привет");
    }

}
