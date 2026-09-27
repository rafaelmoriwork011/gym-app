package com.rvm.gym.repository;

import com.rvm.gym.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, UUID> {
}
