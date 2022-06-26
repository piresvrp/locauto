package com.locauto.locauto.model.repository;

import com.locauto.locauto.model.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}
