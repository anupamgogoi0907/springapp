package com.anupam.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = { "com.anupam.app.dao", "com.anupam.app.service" })
public class SpringAppConfig {

}
