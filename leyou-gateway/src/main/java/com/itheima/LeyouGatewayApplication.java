package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program: leyou
 * @description: zuul的启动类
 * @author: Mr.D
 * @create: 2019-11-03 00:04
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class LeyouGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouGatewayApplication.class);
    }
}
