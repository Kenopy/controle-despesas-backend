package com.financas.despesas.services;

import com.financas.despesas.dtos.DespesaRequestDTO;
import com.financas.despesas.dtos.DespesaResponseDTO;
import com.financas.despesas.models.Despesa;
import com.financas.despesas.repositories.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DespesaService {

    // Injeção de dependência do repositório
    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    // 1. CRIAR
    public DespesaResponseDTO criar(DespesaRequestDTO dto) {
        Despesa novaDespesa = new Despesa();
        novaDespesa.setDescricao(dto.descricao());
        novaDespesa.setValor(dto.valor());
        novaDespesa.setCategoria(dto.categoria());
        novaDespesa.setFormaPagamento(dto.formaPagamento());
        novaDespesa.setDataDespesa(dto.dataDespesa());

        Despesa salva = repository.save(novaDespesa);
        return new DespesaResponseDTO(salva);
    }

    // 2. LISTAR TODAS
    public List<DespesaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(DespesaResponseDTO::new) // Usa aquele construtor prático que fizemos no DTO!
                .toList();
    }

    // 3. BUSCAR POR ID
    public DespesaResponseDTO buscarPorId(UUID id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        return new DespesaResponseDTO(despesa);
    }

    // 4. ATUALIZAR
    public DespesaResponseDTO atualizar(UUID id, DespesaRequestDTO dto) {
        // Primeiro, verificamos se a despesa existe
        Despesa despesaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        // Atualizamos os dados
        despesaExistente.setDescricao(dto.descricao());
        despesaExistente.setValor(dto.valor());
        despesaExistente.setCategoria(dto.categoria());
        despesaExistente.setFormaPagamento(dto.formaPagamento());
        despesaExistente.setDataDespesa(dto.dataDespesa());

        Despesa atualizada = repository.save(despesaExistente);
        return new DespesaResponseDTO(atualizada);
    }

    // 5. DELETAR
    public void deletar(UUID id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        repository.delete(despesa);
    }
}