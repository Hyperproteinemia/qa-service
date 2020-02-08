package com.hyperproteinenimia.qaservice.service;

import com.hyperproteinenimia.qaservice.entity.Question;
import com.hyperproteinenimia.qaservice.exception.QuestionNotFoundException;
import com.hyperproteinenimia.qaservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question getById(Long id) {
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    public void removeById(Long id) {
        questionRepository.deleteById(id);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }
}
