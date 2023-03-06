package kr.kdev.demo.scheduler;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1M")
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "spring.task.scheduling", value = "enabled", havingValue = "true")
public class SchedulingConfiguration {

    @Bean
    public LockProvider lockProvider(RedisConnectionFactory connectionFactory, Environment environment) {
        String env = environment.getProperty("spring.task.scheduling.env", String.class, "default");
        return new RedisLockProvider(connectionFactory, env);
    }
}
