package com.rvm.gym.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreinosResponseDto {

    private List<TreinoResponseDto> treinos = new ArrayList<>();
}
