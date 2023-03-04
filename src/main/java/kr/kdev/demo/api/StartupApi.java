package kr.kdev.demo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.startup.StartupEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/startup")
public class StartupApi {
    private final StartupEndpoint startupEndpoint;

    public StartupApi(final StartupEndpoint startupEndpoint) {
        this.startupEndpoint = startupEndpoint;
    }

    @GetMapping("")
    public ResponseEntity<?> startup() {
        StartupEndpoint.StartupDescriptor startupDescriptor = this.startupEndpoint.startupSnapshot();
        log.trace("{}", startupDescriptor.getTimeline().getStartTime());
        return ResponseEntity.ok(startupDescriptor);
    }
}
