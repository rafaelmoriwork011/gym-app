package com.rvm.gym.repository;

import com.rvm.gym.entity.TreinoConfiguracao;
import com.rvm.gym.entity.Usuario;
import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TreinoConfiguracaoRepository extends JpaRepository<TreinoConfiguracao, UUID> {

    Optional<TreinoConfiguracao> findByUsuarioAndStatus(Usuario usuario, TreinoConfiguracaoStatusEnum status);
}
