package com.rvm.gym.dto.error;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldErrorResponseDto {
    private String field;
    private List<String> message;
}
