package com.rvm.gym.entity;

import com.rvm.gym.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "sexo_id", nullable = false)
    private SexoEnum sexo;

    @Column(name = "altura", precision = 5, scale = 2, nullable = false)
    private BigDecimal altura;

    @Column(name = "peso_atual", precision = 5, scale = 2, nullable = false)
    private BigDecimal pesoAtual;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @CreationTimestamp
    @Column(name = "data_inclusao", nullable = false, updatable = false)
    private LocalDateTime dataInclusao;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;
}
