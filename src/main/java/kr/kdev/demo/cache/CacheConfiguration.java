package kr.kdev.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

@Slf4j
@EnableCaching
@Configuration(proxyBeanMethods = false)
public class CacheConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Scheduled(fixedRateString = "P1D")
    public void clearAll() {
        if (applicationContext != null) {
            CacheManager cacheManager = applicationContext.getBean(CacheManager.class);
            log.info("CacheManager: {}", cacheManager.getClass());

            cacheManager.getCacheNames().stream()
                .map(cacheManager::getCache)
                .filter(Objects::nonNull)
                .forEach(Cache::clear);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
