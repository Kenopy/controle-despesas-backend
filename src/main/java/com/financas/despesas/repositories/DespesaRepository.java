package com.financas.despesas.repositories;

import com.financas.despesas.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DespesaRepository extends JpaRepository<Despesa, UUID> {
    // Métodos de CRUD tradicionais já estão inclusos automaticamente aqui
}