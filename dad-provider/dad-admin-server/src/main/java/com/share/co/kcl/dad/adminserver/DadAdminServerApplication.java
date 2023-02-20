package com.share.co.kcl.dad.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.share.co.kcl.dad"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DadAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DadAdminServerApplication.class, args);
    }

}
