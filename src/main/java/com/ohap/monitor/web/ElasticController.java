package com.ohap.monitor.web;

import com.ohap.monitor.domain.elasticsearch.ElasticSample;
import com.ohap.monitor.service.ElasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ElasticController {

    private final ElasticService elasticService;

    @PostMapping("/elastics")
    public void elasticSave(@RequestBody ElasticSample elasticSample) {
        elasticService.elasticSave(elasticSample);
    }

    @GetMapping("/elastics")
    public Iterable elasticSearchAll() {
        Iterable<ElasticSample> test = elasticService.elasticSearchAll();
        for(ElasticSample test1 : test ){
            System.out.println(test1.getTitle()+", "+test1.getId()+","+test1.getUser());
        }
        return elasticService.elasticSearchAll();
    }

}
