package com.ohap.monitor.web;

import com.ohap.monitor.domain.sample.Sample;
import com.ohap.monitor.service.SampleService;
import com.ohap.monitor.web.dto.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 어노테이션의 특성을 사용하면 @Autowired나 @Resource @Inject 어노테이션 없이 DI 주입이 가능합니다.
 * @RequiredArgsConstructor는 @NonNull이나 final이 붙은 필드에 대해서 생성자를 생성하는데
 * Controller에서 private final XXXService xxxService; 와 같이 final로 선언해주면 해당 필드를 파라미터로 가지는 생성자가 생성됩니다.
 */
@RequiredArgsConstructor
@RestController
public class SampleApiController {

    SampleService sampleService;

    @PostMapping("/ApiSave")
    public void save(@RequestBody SampleSaveRequestDto sampleSaveRequestDto) {
        sampleService.save(sampleSaveRequestDto);
    }
    @PutMapping("/ApiUpdate")
    public void update(@RequestBody SampleSaveRequestDto sampleSaveRequestDto) {
        sampleService.update(sampleSaveRequestDto);
    }

    @PostMapping("/ApiDeleteById")
    public void deleteById(Long id){
        sampleService.deleteById(id);
    }

    @GetMapping("/ApiSelectByIds")
    public void selectById(List<Long> ids){
        List<Sample> list = sampleService.selectByIds(ids);
    }
}

