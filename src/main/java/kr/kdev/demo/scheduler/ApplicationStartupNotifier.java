package kr.kdev.demo.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class ApplicationStartupNotifier {

    @Scheduled(cron = "0 0/10 * * * ?")
    public void info() {
        String appStartTime = System.getProperty("APP_START_TIME");
        Duration duration = Duration.between(Instant.parse(appStartTime), Instant.now());
        log.info("Application has been running for {} hours, {} minutes", duration.toHoursPart(), duration.toMinutesPart());
    }
}
