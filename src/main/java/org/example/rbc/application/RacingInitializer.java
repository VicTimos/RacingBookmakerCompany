package org.example.rbc.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rbc.domain.Racing;
import org.example.rbc.service.RacingService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RacingInitializer implements ApplicationRunner {

    private final RacingService service;

    private static final String RESOURCE_IMAGES_DEF_PATH = "src/main/resources/static/images/";

    @Override
    public void run(ApplicationArguments args) {
        LocalDateTime now = LocalDateTime.now();
        byte[] image1 = null;
        byte[] image2 = null;
        byte[] image3 = null;
        byte[] image4 = null;
        byte[] image5 = null;
        try {
            image1 = Files.readAllBytes(Paths.get(RESOURCE_IMAGES_DEF_PATH + "racing1.jpg").toAbsolutePath());
            image2 = Files.readAllBytes(Paths.get(RESOURCE_IMAGES_DEF_PATH + "racing2.jpg").toAbsolutePath());
            image3 = Files.readAllBytes(Paths.get(RESOURCE_IMAGES_DEF_PATH + "racing3.jpg").toAbsolutePath());
            image4 = Files.readAllBytes(Paths.get(RESOURCE_IMAGES_DEF_PATH + "racing4.jpg").toAbsolutePath());
            image5 = Files.readAllBytes(Paths.get(RESOURCE_IMAGES_DEF_PATH + "racing5.jpg").toAbsolutePath());
        } catch (IOException e) {
            log.warn("Some or all of the racing images were not initialized!");
        }

        service.saveAll(
            List.of(
                new Racing("American racing #1", "Attend one of the biggest racings!", time(now), plusTime(now, 3000L),
                    image1),
                new Racing("United Russian racing #1", "20 cars in Moscow", time(now), plusTime(now, 3500L), image2),
                new Racing("UK MS racing", "News from UK Motor Sporting! Meet us here at 25.05.2022!", time(now), plusTime(now, 2000L), image3),
                new Racing("Tokyo grand championship", "Join us at the latest Tokyo racing!", time(now), plusTime(now, 4000L), image4),
                new Racing("Zimbabwe racing! ", "10 motos in Zimbabwe", time(now), plusTime(now, 1500L), image5)
            )
        );
    }

    private Timestamp time(LocalDateTime time) {
        return Timestamp.valueOf(time);
    }

    private LocalDateTime plusTime(LocalDateTime time, Long adjustment) {
        return time.plus(adjustment, ChronoUnit.MILLIS);
    }
}
