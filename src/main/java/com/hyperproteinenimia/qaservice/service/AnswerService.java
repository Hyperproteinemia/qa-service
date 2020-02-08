package com.hyperproteinenimia.qaservice.service;

import com.hyperproteinenimia.qaservice.entity.Answer;
import com.hyperproteinenimia.qaservice.exception.AnswerHaveNoFoundException;
import com.hyperproteinenimia.qaservice.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(AnswerHaveNoFoundException::new);
    }

    public void removeById(Long id) {
        answerRepository.deleteById(id);
    }
}
