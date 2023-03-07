package kr.kdev.demo.i18n;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

public class CustomResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
    public Properties getMessages(Locale locale) {
        return getMergedProperties(locale).getProperties();
    }
}
