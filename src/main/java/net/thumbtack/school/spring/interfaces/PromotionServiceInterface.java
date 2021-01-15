package net.thumbtack.school.spring.interfaces;

import net.thumbtack.school.spring.models.Recording;

import java.time.ZonedDateTime;

public interface PromotionServiceInterface {

    void createCampaign(Recording recording, ZonedDateTime campaignCreateDate);

}
