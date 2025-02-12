package com.harun.campaignservice.service.impl;

import com.harun.campaignservice.mapper.MapperGenerator;
import com.harun.campaignservice.mapper.MapperGeneratorSingleton;
import com.harun.campaignservice.repository.CampaignRepository;
import com.harun.campaignservice.service.CampaignService;
import com.harun.common.dto.CampaignDTO;
import com.harun.common.enums.ErrorMessage;
import com.harun.entity.models.Campaign;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    MapperGenerator mapper = MapperGeneratorSingleton.INSTANCE;
    private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
    private static String message = "";

    private final CampaignRepository campaignRepository;

    @Override
    public CampaignDTO getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findByIdOrThrowError(id);
        return mapper.campaignToCampaignDTO(campaign);
    }

    @Override
    public CampaignDTO saveCampaign(Campaign campaign) {
        Campaign savedCampaign = campaignRepository.save(campaign);
        return mapper.campaignToCampaignDTO(savedCampaign);
    }

    @Override
    public CampaignDTO updateCampaign(Campaign campaign) {
        Campaign updatedCampaign = campaignRepository.save(campaign);
        return mapper.campaignToCampaignDTO(updatedCampaign);
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.findByIdOrThrowError(id);
        message = ErrorMessage.DELETION_SUCCESS.getMessage("Campaign", id);
        logger.info(message);
        campaignRepository.deleteById(id);
    }
}
