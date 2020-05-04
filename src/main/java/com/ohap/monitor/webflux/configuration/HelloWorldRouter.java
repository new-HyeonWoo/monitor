package com.ohap.monitor.webflux.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.*;

@Component
@RequiredArgsConstructor
public class HelloWorldRouter {
    @Bean
    public RouterFunction<ServerResponse> routes(@Autowired HelloWorldHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET("/TEST"), handler::helloWorld);
    }
}