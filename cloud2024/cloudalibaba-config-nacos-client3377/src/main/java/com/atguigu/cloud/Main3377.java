package com.atguigu.cloud;

import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zzyy
 * @create 2023-05-20 15:59
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Main3377
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main3377.class,args);
    }
}


