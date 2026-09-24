package com.rvm.gym.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfiguracaoTreinoRequestDto {

    @NotNull
    private UUID usuario;

    @Size(max = 255)
    private String observacao;

    @NotNull
    private UUID objetivo;

    @NotEmpty
    @UniqueElements
    @Valid
    private List<MapeamentoTreinoRequestDto> mapeamentosTreinos;

}
