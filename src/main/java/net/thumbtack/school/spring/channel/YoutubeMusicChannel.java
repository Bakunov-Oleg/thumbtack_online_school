package net.thumbtack.school.spring.channel;

import net.thumbtack.school.spring.interfaces.PublishingChannel;
import net.thumbtack.school.spring.models.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class YoutubeMusicChannel implements PublishingChannel {
    private final static Logger LOGGER = LoggerFactory.getLogger(YoutubeMusicChannel.class);

    @Override
    public void publish(Recording recording, ZonedDateTime publishAvailableDate) {
        LOGGER.info("Publish recording {} to YoutubeMusic {}", recording, publishAvailableDate);
    }
}
