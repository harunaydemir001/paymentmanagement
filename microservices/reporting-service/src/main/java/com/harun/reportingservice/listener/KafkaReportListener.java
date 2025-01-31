package com.harun.reportingservice.listener;

import com.harun.common.dto.ReportDTO;
import com.harun.reportingservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaReportListener {

    private final ReportService reportService;


    @KafkaListener(topics = "report-topic", groupId = "report-group")
    public void listen(ReportDTO reportDTO) {
        reportService.saveReport(reportDTO);
    }
}