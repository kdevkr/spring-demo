package kr.kdev.demo.session;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring Session Configuration
 * <a href="https://docs.spring.io/spring-session/reference/index.html">Documentation</a>
 * <p>
 * {@link SessionRepository}가 빈 클래스로 등록되어있지 않다면 {@link SessionAutoConfiguration}에 의해 자동으로 등록된다.
 * 스프링 부트 3.x 부터는 spring.session.store-type의 지원이 사라졌으나 아래와 같이 인 메모리 방식으로도 구성할 수 있다.
 */
@Configuration(proxyBeanMethods = false)
public class SessionConfiguration extends AbstractHttpSessionApplicationInitializer {

    private SessionConfiguration() {
    }

    @ConditionalOnProperty(prefix = "spring.session", value = "store-type", havingValue = "memory")
    @EnableSpringHttpSession
    @Configuration(proxyBeanMethods = false)
    static class InMemorySessionConfiguration {
        @Bean
        public MapSessionRepository sessionRepository() {
            return new MapSessionRepository(new ConcurrentHashMap<>());
        }
    }
}
