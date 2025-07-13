package br.org.sesisenai.DoaVille.controller;

import br.org.sesisenai.DoaVille.dto.MaterialDTO;
import br.org.sesisenai.DoaVille.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiais")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> criar(@RequestBody MaterialDTO dto) {
        return ResponseEntity.ok(materialService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listar() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> buscar(@PathVariable Long id) {
        return materialService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> atualizar(@PathVariable Long id, @RequestBody MaterialDTO dto) {
        return materialService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materialService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}