package com.harun.campaignservice.controller;

import com.harun.campaignservice.service.CampaignService;
import com.harun.common.response.factory.ResponseFactory;
import com.harun.common.response.model.Response;
import com.harun.entity.models.Campaign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/campaign")
@Validated
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCampaignById(@PathVariable Long id) {
        return ResponseFactory.createResponse(campaignService.getCampaignById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Response> saveCampaign(@RequestBody Campaign campaign) {
        return ResponseFactory.createResponse(campaignService.saveCampaign(campaign), HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateCampaign(@RequestBody Campaign campaign) {
        return ResponseFactory.createResponse(campaignService.updateCampaign(campaign), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCampaign(@PathVariable("id") Long id) {
        campaignService.deleteCampaign(id);
        return ResponseFactory.createSuccessResponse();
    }
}
