package com.hyperproteinenimia.qaservice.controller;

import com.hyperproteinenimia.qaservice.service.AnswerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
}
