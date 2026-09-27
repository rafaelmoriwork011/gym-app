package com.rvm.gym.repository;

import com.rvm.gym.entity.TreinoExecucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreinoExecucaoRepository extends JpaRepository<TreinoExecucao, UUID> {

    TreinoExecucao findFirstByTreinoIdInOrderByDataOcorrenciaDesc(List<UUID> treinoIds);

}
