package com.hyperproteinenimia.qaservice.repository;

import com.hyperproteinenimia.qaservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
