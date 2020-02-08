package com.hyperproteinenimia.qaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such question!")
public class QuestionNotFoundException  extends RuntimeException {
}
