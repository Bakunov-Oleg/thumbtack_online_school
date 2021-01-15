package net.thumbtack.school.spring.interfaces;

import net.thumbtack.school.spring.models.Recording;

import java.time.ZonedDateTime;

public interface PublishingChannel {
    void publish(Recording recording, ZonedDateTime publishAvailableDate);
}
