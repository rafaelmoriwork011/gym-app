package com.rvm.gym.repository;

import com.rvm.gym.entity.Exercicio;
import com.rvm.gym.enums.GrupoMuscularEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, UUID> {

    List<Exercicio> findByGrupoMuscularAndIdNotIn(GrupoMuscularEnum grupoMuscular, List<UUID> ids);
}
