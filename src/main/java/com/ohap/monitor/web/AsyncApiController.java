package com.ohap.monitor.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;

/**
 * 어노테이션의 특성을 사용하면 @Autowired나 @Resource @Inject 어노테이션 없이 DI 주입이 가능합니다.
 * @RequiredArgsConstructor는 @NonNull이나 final이 붙은 필드에 대해서 생성자를 생성하는데
 * Controller에서 private final XXXService xxxService; 와 같이 final로 선언해주면 해당 필드를 파라미터로 가지는 생성자가 생성됩니다.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/async")
public class AsyncApiController {

    Queue<DeferredResult<String>> results = new ConcurrentLinkedDeque<>();

    /**
     *
     * 비동기 스트리밍방식으로 데이터를 나눠서 리턴함.
     *
     *
     */
    @GetMapping("/emitter")
    public ResponseBodyEmitter emitter(){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                for(int i=1; i <= 50; i++){
                    emitter.send("<p>Stream " + i + "</p>");
                    Thread.sleep(1000);
                }
            }catch (Exception e){}
        });

        return emitter;
    }


    @GetMapping("/dr")
    public DeferredResult<String> callable(String msg){
        log.info("dr");
        DeferredResult<String> dr = new DeferredResult<>(600000l);
        results.add(dr);
        return dr;
    }

    @GetMapping("/dr/count")
    public String drcount(){
        return String.valueOf(results.size());
    }

    @GetMapping("/dr/event")
    public String drevent(String msg){
        results.stream().forEach(dr -> {
            dr.setResult("Hello " + msg);
            results.remove();
        });

        return "OK";
    }




}

