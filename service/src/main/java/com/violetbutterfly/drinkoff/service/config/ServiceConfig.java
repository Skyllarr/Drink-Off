package com.violetbutterfly.drinkoff.service.config;

import com.violetbutterfly.drinkoff.persistence.PersistenceApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan("com.violetbutterfly.drinkoff.service")
public class ServiceConfig {
}
