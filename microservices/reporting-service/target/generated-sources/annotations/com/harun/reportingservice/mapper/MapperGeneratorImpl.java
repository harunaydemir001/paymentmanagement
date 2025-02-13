package com.harun.reportingservice.mapper;

import com.harun.common.dto.ReportDTO;
import com.harun.reportingservice.model.Report;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-14T00:16:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public ReportDTO reportToReportDTO(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setId( report.getId() );
        reportDTO.setUserId( report.getUserId() );
        reportDTO.setEventType( report.getEventType() );
        reportDTO.setMessage( report.getMessage() );
        Map<String, Object> map = report.getMetadata();
        if ( map != null ) {
            reportDTO.setMetadata( new LinkedHashMap<String, Object>( map ) );
        }

        return reportDTO;
    }

    @Override
    public Report reportDTOToReport(ReportDTO reportDTO) {
        if ( reportDTO == null ) {
            return null;
        }

        Report report = new Report();

        report.setId( reportDTO.getId() );
        report.setUserId( reportDTO.getUserId() );
        report.setEventType( reportDTO.getEventType() );
        report.setMessage( reportDTO.getMessage() );
        Map<String, Object> map = reportDTO.getMetadata();
        if ( map != null ) {
            report.setMetadata( new LinkedHashMap<String, Object>( map ) );
        }

        return report;
    }
}
