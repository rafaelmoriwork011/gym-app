package com.rvm.gym.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Lista de treinos de uma configuração")
public class TreinosResponseDto {

    @Schema(description = "Treinos da configuração")
    @Builder.Default
    private List<TreinoResponseDto> treinos = new ArrayList<>();
}
