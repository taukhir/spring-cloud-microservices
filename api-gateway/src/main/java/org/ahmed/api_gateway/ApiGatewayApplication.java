package org.ahmed.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/products/**")
                        .uri("http://product-service"))
                .route(r -> r.path("/inventory/**")
                        .uri("http://inventory-service"))
                .route(r -> r.path("/orders/**")
                        .uri("http://order-service"))
                .route(r -> r.path("/customers/**")
                        .uri("http://customer-service"))
                .build();
    }
}
