package com.ohap.monitor.domain.sample;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//Get, Set 자동추가
@Getter //@Setter
// 기본 생성자 자동추가
@NoArgsConstructor
// JPA 어노테이션
// - 테이블과 링크될 클래스임을 나타냄
// - 기본값으로 클래스의 카멜케이스 일므을 언더스코어 네이밍으로 이름을 매칭한다.
// - ex) SalesManager.java -> sales_manager table
@Entity
public class Sample {

    //해당테이블의 PK 필드를 나타낸다
    @Id
    //PK 생성 규칙을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블 컬럼을 나타내며, 굳이 선언하지 않아도됨.
    //기본값 이외에 추가로 필요한 옵션이 있을 경우 사용 (기본값 VARCHAR(255))
    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Sample(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
