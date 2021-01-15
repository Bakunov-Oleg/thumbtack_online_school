package net.thumbtack.school.spring.service;

import net.thumbtack.school.spring.TypeTrack;
import net.thumbtack.school.spring.interfaces.PublishingChannel;
import net.thumbtack.school.spring.models.Recording;
import net.thumbtack.school.spring.storage.RecordingDataHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class RecordingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RecordingService.class);
    private final List<PublishingChannel> publishingChannels;
    RecordingDataHub recordingDataHub;

    @Autowired
    public RecordingService(List<PublishingChannel> publishingChannels, RecordingDataHub recordingDataHub) {
        this.publishingChannels = publishingChannels;
        this.recordingDataHub = recordingDataHub;
    }

    public String save(Recording recording) {
        for (PublishingChannel publishingChannel : publishingChannels) {
            publishingChannel.publish(recording, ZonedDateTime.now());
        }
        if (recording.getTypeTrack().equals(TypeTrack.AUDIO)) {
            return recordingDataHub.saveAudio(recording);
        } else if (recording.getTypeTrack().equals(TypeTrack.VIDEO)) {
            return recordingDataHub.saveVideo(recording);
        }
        return null;
    }
}
