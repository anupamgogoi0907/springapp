package com.anupam.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(value = { "com.anupam.app.dao", "com.anupam.app.service", "com.anupam.app.controller" })
public class SpringAppConfig {

}
