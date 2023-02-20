package com.share.co.kcl.dad.appserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.share.co.kcl.dad"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DadAppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DadAppServerApplication.class, args);
    }

}
