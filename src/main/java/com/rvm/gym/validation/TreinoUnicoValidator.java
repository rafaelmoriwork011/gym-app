package com.rvm.gym.validation;

import com.rvm.gym.dto.request.MapeamentoTreinoRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreinoUnicoValidator implements ConstraintValidator<TreinoUnico, List<MapeamentoTreinoRequestDto>> {

    @Override
    public boolean isValid(List<MapeamentoTreinoRequestDto> mapeamentos, ConstraintValidatorContext context) {
        if (mapeamentos == null || mapeamentos.isEmpty()) {
            return true;
        }

        Set<String> nomes = new HashSet<>();
        Set<Integer> ordens = new HashSet<>();

        for (MapeamentoTreinoRequestDto treino : mapeamentos) {
            if (treino.getNome() != null) {
                String nomeUpper = treino.getNome()
                                         .toUpperCase();
                if (!nomes.add(nomeUpper)) {
                    String mensagem = "O nome do treino '" + treino.getNome() + "' está duplicado na lista.";

                    lancarErroCustomizado(context, mensagem);
                    return false;
                }
            }

            if (treino.getOrdem() != null) {
                if (!ordens.add(treino.getOrdem())) {
                    String mensagem = "A ordem " + treino.getOrdem() + " já foi atribuída a outro treino.";

                    lancarErroCustomizado(context, mensagem);
                    return false;
                }
            }
        }

        return true;
    }

    private void lancarErroCustomizado(ConstraintValidatorContext context, String mensagem) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(mensagem)
               .addConstraintViolation();
    }

}
