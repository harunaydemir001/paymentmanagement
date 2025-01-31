package com.harun.reportingservice.repository;

import com.harun.reportingservice.model.Report;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends ElasticsearchRepository<Report, String> {
}
