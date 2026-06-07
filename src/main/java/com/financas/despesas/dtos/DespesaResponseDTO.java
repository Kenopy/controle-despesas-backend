package com.financas.despesas.dtos;

import com.financas.despesas.models.Despesa;
import com.financas.despesas.models.enums.Categoria;
import com.financas.despesas.models.enums.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DespesaResponseDTO(
        UUID id,
        String descricao,
        BigDecimal valor,
        Categoria categoria,
        FormaPagamento formaPagamento,
        LocalDate dataDespesa,
        LocalDateTime dataRegistro
) {
    // Construtor prático para converter a Entidade do banco em um DTO de resposta
    public DespesaResponseDTO(Despesa despesa) {
        this(
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getCategoria(),
                despesa.getFormaPagamento(),
                despesa.getDataDespesa(),
                despesa.getDataRegistro()
        );
    }
}