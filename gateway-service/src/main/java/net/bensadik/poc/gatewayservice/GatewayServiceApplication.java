package net.bensadik.poc.gatewayservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class GatewayServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						r -> r.path("/eureka")
								.filters(f -> f.setPath("/"))
								.uri("http://localhost:8761")
				)
				.route(
						r -> r.path("/eureka/**")
								.uri("http://localhost:8761")
				)
				.route(
						r -> r.path("/api/transfers/**")
								.or()
								.path("/transfer-service/docs")
								.filters(f -> f.rewritePath("/transfer-service/docs", "/v3/api-docs"))
								.uri("lb://customer-service")
				)
				.route(
						r -> r.path("/api/wallets/**")
								.or()
								.path("/wallet-service/docs")
								.filters(f -> f.rewritePath("/wallet-service/docs", "/v3/api-docs"))
								.uri("lb://inventory-service")
				)
				.build();
	}
}
