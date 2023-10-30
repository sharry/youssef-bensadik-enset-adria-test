package net.bensadik.poc.walletservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		net.bensadik.poc.core.exception.handler.GlobalExceptionHandler.class,
})
public class Config {
}
