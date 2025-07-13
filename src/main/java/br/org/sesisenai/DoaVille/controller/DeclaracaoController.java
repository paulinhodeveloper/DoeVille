package br.org.sesisenai.DoaVille.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/declaracoes")
public class DeclaracaoController {

    private final br.org.sesisenai.DoaVille.service.DeclaracaoService declaracaoService;

    public DeclaracaoController(br.org.sesisenai.DoaVille.service.DeclaracaoService declaracaoService) {
        this.declaracaoService = declaracaoService;
    }

    @PostMapping
    public ResponseEntity<br.org.sesisenai.DoaVille.entity.Declaracao> criar(@RequestBody br.org.sesisenai.DoaVille.dto.DeclaracaoDTO dto) {
        return ResponseEntity.ok(declaracaoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<br.org.sesisenai.DoaVille.entity.Declaracao>> listar() {
        return ResponseEntity.ok(declaracaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<br.org.sesisenai.DoaVille.entity.Declaracao> buscar(@PathVariable Long id) {
        return declaracaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        declaracaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}