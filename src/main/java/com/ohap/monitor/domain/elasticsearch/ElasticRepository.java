package com.ohap.monitor.domain.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository("ElasticRepository")
public interface ElasticRepository extends ElasticsearchRepository<ElasticSample,Long> {

    ElasticSample findByUser(String user);
}
