package com.financas.despesas.controllers;

import com.financas.despesas.dtos.DespesaRequestDTO;
import com.financas.despesas.dtos.DespesaResponseDTO;
import com.financas.despesas.services.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/despesas")
@CrossOrigin(origins = "*") // Permitir que front consiga acessar a API sem bloqueios de segurança do navegador (CORS)
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DespesaResponseDTO> criar(@RequestBody DespesaRequestDTO dto) {
        DespesaResponseDTO criada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> listarTodas() {
        List<DespesaResponseDTO> lista = service.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> buscarPorId(@PathVariable UUID id) {
        DespesaResponseDTO despesa = service.buscarPorId(id);
        return ResponseEntity.ok(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> atualizar(@PathVariable UUID id, @RequestBody DespesaRequestDTO dto) {
        DespesaResponseDTO atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}