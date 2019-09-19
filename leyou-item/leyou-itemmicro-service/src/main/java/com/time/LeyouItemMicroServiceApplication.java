package com.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LeyouItemMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouItemMicroServiceApplication.class);
    }
}
