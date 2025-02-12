package com.harun.campaignservice.mapper;

import com.harun.common.dto.CampaignDTO;
import com.harun.entity.models.Campaign;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;


@Mapper(builder = @Builder(disableBuilder = true))
public interface MapperGenerator {
    CampaignDTO campaignToCampaignDTO(Campaign campaign);
}