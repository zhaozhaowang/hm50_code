package com.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
//引入tk.mybatis的@MapperScan
@MapperScan("com.time.item.mapper")
public class LeyouItemMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouItemMicroServiceApplication.class);
    }
}
