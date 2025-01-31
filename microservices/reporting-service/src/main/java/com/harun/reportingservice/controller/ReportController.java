package com.harun.reportingservice.controller;

import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.common.dto.ReportDTO;
import com.harun.reportingservice.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/report")
@Validated
@RequiredArgsConstructor
public class ReportController {
    private final ReportServiceImpl reportService;

    @PostMapping("/create")
    public ResponseEntity<Response> saveReport(@RequestBody ReportDTO reportDTO) {
        return ResponseFactory.createResponse(reportService.saveReport(reportDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getReportById(@PathVariable String id) {
        return ResponseFactory.createResponse(reportService.getReportById(id), HttpStatus.OK);
    }
}
