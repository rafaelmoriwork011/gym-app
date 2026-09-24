package com.rvm.gym.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
