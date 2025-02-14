package com.harun.campaignservice.mapper;

import com.harun.common.dto.CampaignDTO;
import com.harun.entity.models.BankUser;
import com.harun.entity.models.Campaign;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-14T23:34:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MapperGeneratorImpl implements MapperGenerator {

    @Override
    public CampaignDTO campaignToCampaignDTO(Campaign campaign) {
        if ( campaign == null ) {
            return null;
        }

        CampaignDTO campaignDTO = new CampaignDTO();

        campaignDTO.setId( campaign.getId() );
        campaignDTO.setCreatedAt( campaign.getCreatedAt() );
        campaignDTO.setUpdatedAt( campaign.getUpdatedAt() );
        campaignDTO.setName( campaign.getName() );
        campaignDTO.setDiscountPercentage( campaign.getDiscountPercentage() );
        Set<BankUser> set = campaign.getBankUsers();
        if ( set != null ) {
            campaignDTO.setBankUsers( new LinkedHashSet<BankUser>( set ) );
        }
        campaignDTO.setStartDate( campaign.getStartDate() );
        campaignDTO.setEndDate( campaign.getEndDate() );

        return campaignDTO;
    }
}
