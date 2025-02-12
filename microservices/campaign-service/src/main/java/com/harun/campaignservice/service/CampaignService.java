package com.harun.campaignservice.service;

import com.harun.common.dto.CampaignDTO;
import com.harun.entity.models.Campaign;

public interface CampaignService {

    CampaignDTO getCampaignById(Long id);

    CampaignDTO saveCampaign(Campaign campaign);

    CampaignDTO updateCampaign(Campaign campaign);

    void deleteCampaign(Long id);
}
