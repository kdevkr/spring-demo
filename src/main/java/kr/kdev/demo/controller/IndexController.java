package kr.kdev.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
public class IndexController {

    private final MessageSource messageSource;

    public IndexController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public ResponseEntity<String> index(Locale locale) {
        return ResponseEntity.ok(messageSource.getMessage("msg.hello", null, locale));
    }
}
