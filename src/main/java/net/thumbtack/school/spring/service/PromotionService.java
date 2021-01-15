package net.thumbtack.school.spring.service;

import net.thumbtack.school.spring.TypeTrack;
import net.thumbtack.school.spring.interfaces.PromotionServiceInterface;
import net.thumbtack.school.spring.models.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PromotionService implements PromotionServiceInterface {
    private final static Logger LOGGER = LoggerFactory.getLogger(PromotionService.class);

    @Override
    public void createCampaign(Recording recording, ZonedDateTime campaignCreateDate) {
        if (recording.getTypeTrack().equals(TypeTrack.AUDIO)) {
            campaignCreateDate = campaignCreateDate.plusWeeks(1);
        }
        if (recording.getTypeTrack().equals(TypeTrack.VIDEO)) {
            campaignCreateDate = campaignCreateDate.plusWeeks(2);
        }
        LOGGER.info("createCampaign, recording {}, campaignCreateDate {}", recording, campaignCreateDate);
    }
}
