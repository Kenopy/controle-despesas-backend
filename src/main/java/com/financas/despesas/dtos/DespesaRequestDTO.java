package com.financas.despesas.dtos;

import com.financas.despesas.models.enums.Categoria;
import com.financas.despesas.models.enums.FormaPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaRequestDTO(
        String descricao,
        BigDecimal valor,
        Categoria categoria,
        FormaPagamento formaPagamento,
        LocalDate dataDespesa
) {
}