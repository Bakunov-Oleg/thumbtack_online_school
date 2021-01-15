package net.thumbtack.school.spring;

import net.thumbtack.school.spring.interfaces.PromotionServiceInterface;
import net.thumbtack.school.spring.models.Recording;
import net.thumbtack.school.spring.service.PromotionService;
import net.thumbtack.school.spring.service.RecordingService;
import net.thumbtack.school.spring.storage.RecordingDataHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.ZonedDateTime;

@Component
public class CmdOperations implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(CmdOperations.class);

    private final PromotionServiceInterface promotionService;
    private final RecordingService recordingService;

    @Autowired
    public CmdOperations(PromotionService promotionService, RecordingService recordingService) {
        this.promotionService = promotionService;
        this.recordingService = recordingService;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Hello, runner was started, {}", 123);
        LOGGER.info("Runner was started");
        Recording recording = new Recording("Вася",
                TypeTrack.AUDIO,
                "wert",
                "postrew",
                2020,
                new URL("https://moproprt.ru"),
                GenreTrack.ROCK,
                1.20,
                new URL("https://yande.xMusic"));

        recordingService.save(recording);
        promotionService.createCampaign(recording, ZonedDateTime.now());

        Recording recording1 = new Recording("Postom",
                TypeTrack.VIDEO,
                "aswkern",
                "neslat",
                2020,
                new URL("https://resvisio.ru"),
                GenreTrack.ROCK,
                1.20,
                new URL("https://geronia.xMusic"));

        recordingService.save(recording1);
        promotionService.createCampaign(recording1, ZonedDateTime.now());
        LOGGER.info("Runner was stopped");
    }

}
