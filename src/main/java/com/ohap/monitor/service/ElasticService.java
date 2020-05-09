package com.ohap.monitor.service;

import com.ohap.monitor.domain.elasticsearch.ElasticRepository;
import com.ohap.monitor.domain.elasticsearch.ElasticSample;
import com.ohap.monitor.web.dto.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticService {

    private final ElasticRepository elasticRepository;

    //elasticSave
    public void elasticSave(ElasticSample elasticSample){
        elasticRepository.save(elasticSample);
    }

    //elasticSearchAll
    public Iterable<ElasticSample> elasticSearchAll(){
        return elasticRepository.findAll();
    }


}
