package net.thumbtack.school.spring.storage;

import net.thumbtack.school.spring.interfaces.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class AudioRecordingStorage implements DataStorage {

    private final static Logger LOGGER = LoggerFactory.getLogger(AudioRecordingStorage.class);

    @Override
    public String save(String path) {
        if (path == null) {
            LOGGER.info("Audio recording path IS NULL");
            return null;
        } else {
            LOGGER.info("Save Audio recording to path {}", path);
            return path;
        }
    }

}
