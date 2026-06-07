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
@CrossOrigin(origins = "*") // Importante: Permite que seu futuro front-end (Angular) consiga acessar a API sem bloqueios de segurança do navegador (CORS)
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    // 1. POST: http://localhost:8080/api/despesas
    @PostMapping
    public ResponseEntity<DespesaResponseDTO> criar(@RequestBody DespesaRequestDTO dto) {
        DespesaResponseDTO criada = service.criar(dto);
        // Retorna 201 (Created) quando algo novo é salvo com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    // 2. GET: http://localhost:8080/api/despesas
    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> listarTodas() {
        List<DespesaResponseDTO> lista = service.listarTodas();
        // Retorna 200 (OK) com a lista
        return ResponseEntity.ok(lista);
    }

    // 3. GET: http://localhost:8080/api/despesas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> buscarPorId(@PathVariable UUID id) {
        DespesaResponseDTO despesa = service.buscarPorId(id);
        return ResponseEntity.ok(despesa);
    }

    // 4. PUT: http://localhost:8080/api/despesas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> atualizar(@PathVariable UUID id, @RequestBody DespesaRequestDTO dto) {
        DespesaResponseDTO atualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    // 5. DELETE: http://localhost:8080/api/despesas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        // Retorna 204 (No Content) indicando que deu certo, mas não há o que mostrar na tela
        return ResponseEntity.noContent().build();
    }
}