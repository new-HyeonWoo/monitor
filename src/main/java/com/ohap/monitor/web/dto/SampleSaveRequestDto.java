package com.ohap.monitor.web.dto;

import com.ohap.monitor.domain.sample.Sample;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.StringContent;

@Getter //@Setter
// 기본 생성자 자동추가
@NoArgsConstructor
public class SampleSaveRequestDto {

    private Long id;

    private String title;

    private String content;

    @Builder
    public SampleSaveRequestDto(String title,String content,Long id){
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public Sample toEntity(){
        return Sample.builder().title(title).content(content).id(id).build();
    }
}
