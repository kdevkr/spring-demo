package kr.kdev.demo.scheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "spring.task.scheduling", value = "enabled", havingValue = "true")
public class SchedulingConfiguration {
}
