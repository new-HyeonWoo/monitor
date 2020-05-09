package com.ohap.monitor.service;

import com.ohap.monitor.domain.sample.Sample;
import com.ohap.monitor.domain.sample.SampleRepository;
import com.ohap.monitor.web.dto.SampleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional
    public void save(SampleSaveRequestDto sampleSaveRequestDto){
        sampleRepository.save(sampleSaveRequestDto.toEntity());
    }

    public void deleteById(Long id){
        sampleRepository.deleteById(id);
    }

    public void update(SampleSaveRequestDto sampleSaveRequestDto){
        sampleRepository.save(sampleSaveRequestDto.toEntity());
    }

    public List<Sample> selectByIds(List<Long> ids){
        return sampleRepository.findAllById(ids);
    }
}
