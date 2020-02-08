package com.hyperproteinenimia.qaservice.controller;

import com.hyperproteinenimia.qaservice.service.QuestionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


}
