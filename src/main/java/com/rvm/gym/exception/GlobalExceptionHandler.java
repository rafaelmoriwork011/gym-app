package com.rvm.gym.exception;

import com.rvm.gym.dto.error.ErrorResponseDto;
import com.rvm.gym.dto.error.FieldErrorResponseDto;
import com.rvm.gym.helper.ValidationExceptionHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ValidationExceptionHelper validationExceptionHelper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldErrorResponseDto> fieldErrors = validationExceptionHelper.toFieldDTOList(ex.getBindingResult()
                                                                                             .getFieldErrors());

        var messages = new ArrayList<String>();
        messages.add("Um ou mais erros de validação encontrados.");

        ErrorResponseDto dto = ErrorResponseDto.builder()
                .messages(messages)
                .fieldErrors(fieldErrors)
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                             .body(dto);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatus(ResponseStatusException ex, HttpServletRequest request) {

        String message = ex.getReason() != null ? ex.getReason() : ex.getMessage();

        ErrorResponseDto dto = ErrorResponseDto.builder()
                .messages(List.of(message))
                .build();

        return ResponseEntity.status(ex.getStatusCode())
                             .body(dto);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusiness(BusinessException ex) {
        ErrorResponseDto dto = ErrorResponseDto.builder()
                .messages(List.of(ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                             .body(dto);
    }

}
