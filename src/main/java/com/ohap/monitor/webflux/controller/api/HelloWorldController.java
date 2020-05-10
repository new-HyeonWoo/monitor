package com.ohap.monitor.webflux.controller.api;

import com.ohap.monitor.webflux.model.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//@RestController
//public class HelloWorldController {
//
//    @GetMapping("/webflux/hello")
//    public Mono<HelloWorld> hello(){
//        HelloWorld helloWorld = new HelloWorld();
//        helloWorld.setTitle("hello");
//        helloWorld.setMessage("hi");
//        return Mono.just(helloWorld);
//    }
//}
