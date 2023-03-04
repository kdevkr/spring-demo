package kr.kdev.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.time.Instant;

@Slf4j
public class ApplicationStartedListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.setProperty("APP_START_TIME", Instant.now().toString());
    }
}
