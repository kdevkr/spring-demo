package kr.kdev.demo.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class ApplicationStartupNotifier {

    @SchedulerLock(name = "ApplicationStartupNotifier.info", lockAtLeastFor = "PT9M", lockAtMostFor = "PT9M59S")
    @Scheduled(cron = "0 0/10 * * * ?")
    public void info() {
        String appStartTime = System.getProperty("APP_START_TIME");
        Instant startTime = Instant.parse(appStartTime);
        Duration duration = Duration.between(startTime, Instant.now());
        log.info("Application has been running for {} hours, {} minutes after {}", duration.toHoursPart(), duration.toMinutesPart(), startTime);
    }
}
