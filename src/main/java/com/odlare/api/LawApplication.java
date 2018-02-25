package com.odlare.api;

import com.odlare.api.config.security.property.EnableHttpsProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({EnableHttpsProperty.class, JpaProperties.class})
public class LawApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawApplication.class, args);
    }
}
