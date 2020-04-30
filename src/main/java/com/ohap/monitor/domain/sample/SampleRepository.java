package com.ohap.monitor.domain.sample;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * MyBatis에서 Dao라고 불리는 DB Layer 접근자이다.
 * JPA에선 Repository라고 부르며 인터페이스로 생성한다.
 * 단순 인터페이스 생성 후, JpaRepository<Entity 클래스, PK타입>를 상속하면 기본적인 CRUD 메소드가 자동생성 된다.
 * ※주의 : Entity클래스와 기본 Repository는 함께 위채해야 한다.
 */
public interface SampleRepository extends JpaRepository<Sample, Long> {

    List<Sample> findByTitle(String title);

}
