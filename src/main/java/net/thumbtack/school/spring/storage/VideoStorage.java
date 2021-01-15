package net.thumbtack.school.spring.storage;

import net.thumbtack.school.spring.interfaces.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VideoStorage implements DataStorage {
    private final static Logger LOGGER = LoggerFactory.getLogger(VideoStorage.class);

    @Override
    public String save(String path) {
        if (path == null) {
            LOGGER.info("Video recording path IS NULL");
            return null;
        } else {
            LOGGER.info("Save video recording to path {}", path);
            return path;
        }
    }

}
