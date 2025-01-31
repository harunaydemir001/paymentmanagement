package com.harun.reportingservice.service;

import com.harun.common.dto.ReportDTO;

public interface ReportService {

    ReportDTO saveReport(ReportDTO reportDTO);

    ReportDTO getReportById(String id);
}
