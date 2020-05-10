//package com.ohap.monitor.webflux.configuration;
//
//import com.ohap.monitor.webflux.model.HelloWorld;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class HelloWorldHandler {
//
//    public Mono<ServerResponse> helloWorld(ServerRequest request) {
//        Mono<HelloWorld> helloworldMono = Mono.just(new HelloWorld(1, "hello", "hi"));
//        return ServerResponse.ok().body(helloworldMono, HelloWorld.class);
//    }
//}