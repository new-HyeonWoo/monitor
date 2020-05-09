package com.ohap.monitor.domain.elastic;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(indexName = "index", type = "elastics")
public class ElasticSample {

    @Id
    private String id;
    private String title;
    private String user;
    private long startAt;
    private long endAt;
}
