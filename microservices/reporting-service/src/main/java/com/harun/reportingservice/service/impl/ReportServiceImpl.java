package com.harun.reportingservice.service.impl;

import com.harun.common.dto.ReportDTO;
import com.harun.reportingservice.mapper.MapperGenerator;
import com.harun.reportingservice.mapper.MapperGeneratorSingleton;
import com.harun.reportingservice.model.Report;
import com.harun.reportingservice.repository.ReportRepository;
import com.harun.reportingservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;

    private final ReportRepository reportRepository;

    @Override
    public ReportDTO saveReport(ReportDTO reportDTO) {
        Report report = mapper.reportDTOToReport(reportDTO);
        reportRepository.save(report);
        return mapper.reportToReportDTO(report);
    }

    @Override
    public ReportDTO getReportById(String id) {
        Report report = reportRepository.findById(id).orElseThrow();
        return mapper.reportToReportDTO(report);
    }


}
