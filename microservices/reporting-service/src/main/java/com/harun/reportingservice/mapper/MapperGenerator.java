package com.harun.reportingservice.mapper;


import com.harun.common.dto.ReportDTO;
import com.harun.reportingservice.model.Report;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {
    ReportDTO reportToReportDTO(Report report);

    Report reportDTOToReport(ReportDTO reportDTO);
}