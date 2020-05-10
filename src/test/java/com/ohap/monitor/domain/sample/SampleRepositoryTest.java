//package com.ohap.monitor.domain.sample;
//
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
////@ExtendWith(SpringExtension.class)
////@SpringBootTest
//public class SampleRepositoryTest {
//
//    @Autowired
//    SampleRepository sampleRepository;
//
//    @AfterEach
//    public void cleanup(){
//
//    }
//
//    @Test
//    public void 샘플저장_불러오기(){
//        String title = "title";
//        String content = "content";
//
//        sampleRepository.save(Sample.builder()
//                .title(title)
//                .content(content)
//                .build());
//
//        List<Sample> sampleList = sampleRepository.findAll();
//
//        Sample sample = sampleList.get(0);
//
//        assertThat(sample.getTitle()).isEqualTo(title);
//        assertThat(sample.getContent()).isEqualTo(content);
//
//    }
//
//
//}
