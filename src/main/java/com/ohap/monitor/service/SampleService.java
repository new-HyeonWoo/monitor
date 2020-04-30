package com.ohap.monitor.service;

import com.ohap.monitor.domain.sample.Sample;
import com.ohap.monitor.domain.sample.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SampleService {

    SampleRepository sampleRepository;

    public List<Sample> returnName(String title){
        List<Sample> data = sampleRepository.findByTitle(title);
        return data;
    }
}
