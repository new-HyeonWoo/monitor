package com.ohap.monitor.web;

import com.ohap.monitor.domain.sample.Sample;
import com.ohap.monitor.domain.sample.SampleRepository;
import com.ohap.monitor.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 어노테이션의 특성을 사용하면 @Autowired나 @Resource @Inject 어노테이션 없이 DI 주입이 가능합니다.
 * @RequiredArgsConstructor는 @NonNull이나 final이 붙은 필드에 대해서 생성자를 생성하는데
 * Controller에서 private final XXXService xxxService; 와 같이 final로 선언해주면 해당 필드를 파라미터로 가지는 생성자가 생성됩니다.
 */
@RequiredArgsConstructor
@RestController
public class SampleApiController {

   // SampleRepository sampleRepository;

    SampleService service;

    @PutMapping("/hello/{title}")
    public List<Sample> returnName(@PathVariable String title){
        List<Sample> data = service.returnName(title);
        return data;
    }





}

