package com.hyperproteinenimia.qaservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperproteinenimia.qaservice.entity.Answer;
import com.hyperproteinenimia.qaservice.entity.Question;
import com.hyperproteinenimia.qaservice.exception.UserHaveNoPermissionToModifyFile;
import com.hyperproteinenimia.qaservice.service.AnswerService;
import com.hyperproteinenimia.qaservice.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping("/qa/answer/")
    public ResponseEntity<String> getAll() {
        HttpStatus httpStatus;
        String jsonResponse;

        List<Answer> articles = answerService.getAll();

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

    @GetMapping("/aq/answer/{id}")
    public ResponseEntity<String> getAnswersByQuestionId(@PathVariable Long id) {
        HttpStatus httpStatus;
        String jsonResponse;

        Question question = questionService.getById(id);
        Set<Answer> answers = question.getAnswers();

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(answers);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "Failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }

    @PostMapping("qa/answer/{id}")
    public ResponseEntity<String> addAnswer(@RequestHeader(name = "username") String username,
                                            @RequestBody Answer answer, @PathVariable Long id) {
        answer.setUsername(username);
        Question question = questionService.getById(id);
        question.getAnswers().add(answer);
        questionService.updateQuestion(question);

        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }

    @DeleteMapping("qa/answer/{id}")
    public ResponseEntity<String> addAnswer(@RequestHeader(name = "username") String username,
                                            @PathVariable Long id) {
        Answer answer = answerService.getById(id);
        if (!answer.getUsername().equals(username)) {
            throw new UserHaveNoPermissionToModifyFile();
        }
        answerService.removeById(id);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }
}
