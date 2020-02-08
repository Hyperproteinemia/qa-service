package com.hyperproteinenimia.qaservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperproteinenimia.qaservice.entity.Question;
import com.hyperproteinenimia.qaservice.exception.UserHaveNoPermissionToModifyFile;
import com.hyperproteinenimia.qaservice.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/qa/question")
    public ResponseEntity<String> getAllQuestions() {
        HttpStatus httpStatus;
        String jsonResponse;

        List<Question> articles = questionService.getAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(articles);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "Failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }

    @PostMapping("/qa/question")
    public ResponseEntity<String> addQuestion(@RequestHeader(name = "username") String username,
                                              @RequestBody Question question) {
        question.setUsername(username);
        questionService.addQuestion(question);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }

    @DeleteMapping("/qa/question/{id}")
    public ResponseEntity<String> addQuestion(@RequestHeader(name = "username") String username,
                                              @PathVariable Long id) {
        Question question = questionService.getById(id);
        if (!question.getUsername().equals(username)) {
            throw new UserHaveNoPermissionToModifyFile();
        }
        questionService.removeById(id);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }
}
