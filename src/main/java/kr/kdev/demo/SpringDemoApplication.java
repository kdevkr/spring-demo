package kr.kdev.demo;

import kr.kdev.demo.listener.ApplicationStartedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

@SpringBootApplication
public class SpringDemoApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringDemoApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.addListeners(new ApplicationStartedListener());
        application.run(args);
    }
}
