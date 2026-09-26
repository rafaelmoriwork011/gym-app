package com.rvm.gym.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TreinoUnicoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TreinoUnico {

    String message() default "Não podem existir nomes ou ordens duplicadas nos mapeamentos de treinos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
