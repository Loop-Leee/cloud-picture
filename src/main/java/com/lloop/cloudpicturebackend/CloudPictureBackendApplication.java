package com.lloop.cloudpicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.lloop.cloudpicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class CloudPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudPictureBackendApplication.class, args);
    }

}