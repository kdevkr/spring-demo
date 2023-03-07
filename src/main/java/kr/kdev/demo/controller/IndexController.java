package kr.kdev.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;


@Controller
public class IndexController {

    private final MessageSource messageSource;

    public IndexController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String index(Locale locale, Model model) {
        model.addAttribute("msgHello", messageSource.getMessage("msg.hello", null, locale));
        return "index";
    }
}
