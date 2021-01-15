package net.thumbtack.school.spring.storage;

import net.thumbtack.school.spring.interfaces.DataStorage;
import net.thumbtack.school.spring.models.Recording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecordingDataHub {

    private final static Logger LOGGER = LoggerFactory.getLogger(RecordingDataHub.class);
    private DataStorage audioStorage;
    private DataStorage videoStorage;


    @Autowired
    public RecordingDataHub(@Qualifier("audioRecordingStorage") DataStorage audioStorage, @Qualifier("videoStorage") DataStorage videoStorage) {
        this.audioStorage = audioStorage;
        this.videoStorage = videoStorage;
    }

    public String saveAudio(Recording recording) {
        return audioStorage.save(recording.getContentUrl().toString());
    }

    public String saveVideo(Recording recording) {
        return videoStorage.save(recording.getContentUrl().toString());
    }
}
