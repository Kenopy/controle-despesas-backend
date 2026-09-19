package com.financas.despesas.services;

import com.financas.despesas.dtos.DespesaRequestDTO;
import com.financas.despesas.dtos.DespesaResponseDTO;
import com.financas.despesas.models.Despesa;
import com.financas.despesas.models.enums.FormaPagamento;
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

    public DespesaResponseDTO criar(DespesaRequestDTO dto) {
        Despesa novaDespesa = new Despesa();
        novaDespesa.setDescricao(dto.descricao());
        novaDespesa.setValor(dto.valor());
        novaDespesa.setCategoria(dto.categoria());
        novaDespesa.setFormaPagamento(dto.formaPagamento());
        novaDespesa.setDataDespesa(dto.dataDespesa());

        // Futuramente, quando o front tiver pronto, pensar se envia do front o 1 por padrao ou se envia null e definimos aqui, para metodos diferentes de Cartao_Credito
        // Fazer tbm para o metodo atualizar
        if (dto.formaPagamento() == FormaPagamento.CARTAO_CREDITO)
        {
            int qtdParcelas = (dto.parcelas() != null && dto.parcelas() > 0) ? dto.parcelas() : 1;
            novaDespesa.setParcelas(qtdParcelas);
        } else {
            novaDespesa.setParcelas(1);
        }

        Despesa salva = repository.save(novaDespesa);
        return new DespesaResponseDTO(salva);
    }

    public List<DespesaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(DespesaResponseDTO::new) // Usa aquele construtor prático que fizemos no DTO!
                .toList();
    }

    public DespesaResponseDTO buscarPorId(UUID id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        return new DespesaResponseDTO(despesa);
    }

    public DespesaResponseDTO atualizar(UUID id, DespesaRequestDTO dto) {
        Despesa despesaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        despesaExistente.setDescricao(dto.descricao());
        despesaExistente.setValor(dto.valor());
        despesaExistente.setCategoria(dto.categoria());
        despesaExistente.setFormaPagamento(dto.formaPagamento());
        despesaExistente.setDataDespesa(dto.dataDespesa());

        Despesa atualizada = repository.save(despesaExistente);
        return new DespesaResponseDTO(atualizada);
    }

    public void deletar(UUID id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada com o ID: " + id));

        repository.delete(despesa);
    }
}