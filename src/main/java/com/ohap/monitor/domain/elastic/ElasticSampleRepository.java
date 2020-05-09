package com.ohap.monitor.domain.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository("ElasticSampleRepository")
public interface ElasticSampleRepository extends ElasticsearchRepository<ElasticSample, String> {

    ElasticSample findByUser(String user);

}
