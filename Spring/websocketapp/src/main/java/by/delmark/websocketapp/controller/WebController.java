package by.delmark.websocketapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/web/chat")
public class WebController {

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
