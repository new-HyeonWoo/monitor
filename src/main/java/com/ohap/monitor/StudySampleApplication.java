package com.ohap.monitor;

import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootApplication
public class StudySampleApplication {
    @RestController
    public static class MyController{
        AsyncRestTemplate rt3 = new AsyncRestTemplate(new Netty4ClientHttpRequestFactory(new NioEventLoopGroup(1)));
        // 비동기처리하기위해 백그라운드 쓰레드만듬. 좋지않은방법.
        AsyncRestTemplate rt2 = new AsyncRestTemplate();

        RestTemplate rt = new RestTemplate();

        @GetMapping("/rest")
        public String rest(int idx){
            // Blocking
            String res = rt.getForObject("http://localhost:8081/service?req={req}", String.class,
                    "hello" + idx);
            return res;
        }

        @GetMapping("/rest2")
        public ListenableFuture<ResponseEntity<String>> rest2(int idx){
            // Defered -> Event 필요.
            // Callable -> workingThread 필요.
            // Future Type -> 비동기 수행작업 결과로 가져올때 사용.

            // Non-Blocking -> Spring MVC가 알아서 Callback 해줌
            return rt2.getForEntity("http://localhost:8081/service?req={req}", String.class,
                    "hello" + idx);
        }

        @GetMapping("/rest3")
        public DeferredResult<String> rest3(int idx){
            DeferredResult<String> dr = new DeferredResult<>();

            ListenableFuture<ResponseEntity<String>> f1 = rt3.getForEntity("http://localhost:8081/service?req={req}", String.class,
                    "hello" + idx);

            // f1.get() -> Blocking
            f1.addCallback(s -> {
                dr.setResult(s.getBody() + "/work");
            }, e -> {
                dr.setErrorResult(e.getMessage());
            });


            return dr;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(StudySampleApplication.class, args);
    }
}
